package com.example.tp_frontend_androidapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.Reserva;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import actividadPaciente.PacientesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CrearReservaActivity extends AppCompatActivity{
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;


    Spinner spinner_reserva;

    String fecha;
    Doctor doctor;
    Paciente paciente;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reserva);
        spinner_reserva = findViewById(R.id.horario_reserva_spinner);
        etFecha=findViewById(R.id.et_mostrar_fecha_picker);



    }

    public void obtenerFecha(View v){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                fecha = year + mesFormateado + diaFormateado;
                if(doctor!=null){
                    cargarHorarios();
                }

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void cargarHorarios(){
        Call<Reserva[]> callReserva=Servicios.getReservaService().obtenerHorarios(doctor.getIdPersona(),fecha,"S");
        callReserva.enqueue(new Callback<Reserva[]>() {
            @Override
            public void onResponse(Call<Reserva[]> call, Response<Reserva[]> response) {
                Reserva[] items=response.body();
                if((items != null ? items.length : 0) ==0){
                    etFecha.setError("No hay horario en esta fecha");

                }
                else{
                    etFecha.setError(null);
                }
                for(Reserva r: items != null ? items : new Reserva[0]){
                    if(r.getHoraFin().length()==8){//SI EL FORMATO ES HH:MM:SS
                        r.setHoraFinCadena(r.getHoraFin().substring(0,5));
                    }
                    else{//SI ES ESTE FORMATO: 1970-01-01 14:20:00
                        r.setHoraFinCadena(r.getHoraFin().substring(11,16));

                    }
                    if(r.getHoraInicio().length()==8){//SI EL FORMATO ES HH:MM:SS
                        r.setHoraInicioCadena(r.getHoraInicio().substring(0,5));
                    }
                    else{//SI ES ESTE FORMATO: 1970-01-01 14:20:00
                        r.setHoraInicioCadena(r.getHoraInicio().substring(11,16));

                    }
                }
                ArrayAdapter<Reserva> adapter=new ArrayAdapter<>(CrearReservaActivity.this, android.R.layout.simple_list_item_1,items);
                spinner_reserva.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Reserva[]> call, Throwable t) {
                Log.d("sad","as");
            }
        });


    }



    public void abrir(View v) {
        String tag = v.getTag().toString();
        Intent intentNewActivity;
        Bundle b = new Bundle();
        //b.putString("usuario",campoNombreUsuario.getText().toString());
        switch (tag) {
            case "Doctores":
                Intent i = new Intent(this, PacientesActivity.class);
                i.putExtra("busqueda","doctor");
                startActivityForResult(i, 40);
                if(fecha!=null){
                    cargarHorarios();
                }

                break;
            case "Pacientes":
                Intent i1 = new Intent(this, PacientesActivity.class);
                i1.putExtra("busqueda","paciente");
                startActivityForResult(i1, 41);

                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
            switch (requestCode){

                case 40:
                    doctor= new Doctor();
                    TextView txtdoc= findViewById(R.id.textDoc);
                    txtdoc.setText(data.getStringExtra("doctorNombre"));
                    doctor.setIdPersona(data.getIntExtra("doctorId", 0));
                    break;
                case 41:
                    paciente= new Paciente();
                    TextView txtpac= findViewById(R.id.textPac);
                    txtpac.setText(data.getStringExtra("pacienteNombre"));
                    paciente.setIdPersona(data.getIntExtra("pacienteId", 0));

            }
        }
    }

    public void guardar(View v){

        String diaFormateado = (c.get(Calendar.DATE) < 10)? CERO + String.valueOf(c.get(Calendar.DATE)):String.valueOf(c.get(Calendar.DATE));
        int mes=c.get(Calendar.MONTH)+1;
        String mesFormateado = (mes < 10)? CERO + String.valueOf(mes):String.valueOf(mes);

        String hoy=c.get(Calendar.YEAR)+mesFormateado+diaFormateado;
        //System.out.println("elegida: "+this.fecha+" hoy: "+hoy);
        if(this.paciente==null){
            Toast.makeText(CrearReservaActivity.this, "No hay paciente seleccionado", Toast.LENGTH_SHORT).show();
            return;
        }
        if(this.doctor==null){
            Toast.makeText(CrearReservaActivity.this, "No hay doctor seleccionado", Toast.LENGTH_SHORT).show();
            return;
        }
        if(this.fecha==null){
            Toast.makeText(CrearReservaActivity.this, "No hay fecha seleccionada", Toast.LENGTH_SHORT).show();
        }
        Reserva hor=(Reserva) spinner_reserva.getSelectedItem();
        if(hor == null){
            Toast.makeText(CrearReservaActivity.this,"Seleccione al menos un horario",Toast.LENGTH_SHORT).show();
            return;

        }
        if(etFecha.getError()!=null){
            return;
        }
        if(Integer.parseInt(this.fecha) < Integer.parseInt(hoy)){
            Toast.makeText(CrearReservaActivity.this, "Elija bien una fecha", Toast.LENGTH_SHORT).show();
            return;

        }

        final Reserva reserva= new Reserva();
        reserva.setFechaCadena(this.fecha);
        reserva.setHoraInicioCadena(hor.getHoraInicioCadena().replace(":",""));
        reserva.setHoraFinCadena(hor.getHoraFinCadena().replace(":",""));
        reserva.setIdEmpleado(this.doctor);
        reserva.setIdCliente(this.paciente);

        Call<Reserva> callguardar = Servicios.getReservaService().cargarReserva(reserva, "pedro");
        callguardar.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                if(response.code()<400) {
                    Log.d("id", response.body().getIdReserva().toString());
                    Toast.makeText(CrearReservaActivity.this, "Creado Correctamente!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(CrearReservaActivity.this, "Ocurrio un error!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Toast.makeText(CrearReservaActivity.this, "Ocurrio un error!!", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
