package com.example.tp_frontend_androidapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.Reserva;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarAgregarReservaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Reserva reserva_modificar = null;
    Integer idReservaEditar = null;
    EditText txtObservacion;
    CheckBox AsistioSi;
    Paciente paciente;
    Paciente doctor;
    TextView paciente_txt;
    TextView doctor_txt;
    TextView HoraInicio_txt;
    TextView HoraFin_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_agregar_reserva);
        txtObservacion=findViewById(R.id.txtObservacion);
        AsistioSi = (CheckBox) findViewById(R.id.ckAsistioSi);
        paciente_txt = findViewById(R.id.txtPaciente);
        doctor_txt = findViewById(R.id.txtDoctor);
        HoraInicio_txt = findViewById(R.id.txtHoraInicio);
        HoraFin_txt = findViewById(R.id.txtHoraFin);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle!=null && bundle.containsKey("reservaEditar")) {
            reserva_modificar = (Reserva) bundle.getSerializable("reservaEditar");
            txtObservacion.setEnabled(true);
            AsistioSi.setEnabled(true);
            cargarCampos();
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    public void cancelarReserva(View v){
        Call<Integer> callCancelarReserva = Servicios.getReservaService().cancelarReserva(reserva_modificar.getIdReserva());
        callCancelarReserva.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(EditarAgregarReservaActivity.this,"Reserva cancelada",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void cargarCampos(){
        txtObservacion.setText(reserva_modificar.getObservacion());

        doctor_txt.setText(reserva_modificar.getIdEmpleado().getNombre());
        paciente_txt.setText(reserva_modificar.getIdCliente().getNombre());
        HoraInicio_txt.setText(reserva_modificar.getHorainicioCadena());
        HoraFin_txt.setText(reserva_modificar.getHorafinCadena());
    }

    public void guardar(View view) {
        if(isEmpty(txtObservacion)){
            setError(txtObservacion,"Campo requerido");
            return;
        }

        Reserva reserva=new Reserva();
        if(reserva_modificar != null){

            reserva.setIdReserva(reserva_modificar.getIdReserva());
            reserva.setObservacion(txtObservacion.getText().toString());

            reserva.setFlagAsistio(AsistioSi.isChecked()?"S":"N");
        }

        Call<Reserva> callReserva = Servicios.getReservaService().modificarReserva(reserva,"pedro");
        callReserva.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                Toast.makeText(EditarAgregarReservaActivity.this,"Reservada modificada exitosamente",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.w("warning", t.getCause().toString());
            }
        });
    }
    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }
}
