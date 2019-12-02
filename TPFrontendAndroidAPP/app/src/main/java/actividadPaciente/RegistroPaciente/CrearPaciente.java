package actividadPaciente.RegistroPaciente;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.R;
import com.google.android.material.textfield.TextInputEditText;

import actividadPaciente.Modelo.Paciente;
import actividadPaciente.PacientesActivity;
import actividadPaciente.Servicio.ApiPaciente;
import actividadPaciente.dialog.DatePickerFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPaciente extends AppCompatActivity {

    private final String url = "http://gy7228.myfoscam.org:8080/stock-pwfe/";
    private Retrofit retrofit;

    private TextInputEditText nombreInput;
    private TextInputEditText apellidoInput;
    private TextInputEditText emailInput;
    private TextInputEditText telofonoInput;
    private TextInputEditText rucInput;
    private TextInputEditText cedulaInput;
    private TextInputEditText fechaNacimientoInput;
    private Button botonRegistrar;

    private Paciente paciente_modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        getSupportActionBar().setTitle("Registro Paciente");

        RetrofitClient();
        createUp();
        if(getIntent().getSerializableExtra("paciente")!=null){
            paciente_modificar =(Paciente)getIntent().getSerializableExtra("paciente");
            llenarCampos();
        }
    }

    private void llenarCampos(){
        nombreInput.setText(paciente_modificar.getNombre());
        apellidoInput.setText(paciente_modificar.getApellido());
        emailInput.setText(paciente_modificar.getEmail());
        telofonoInput.setText(paciente_modificar.getTelefono());
        rucInput.setText(paciente_modificar.getRuc());
        cedulaInput.setText(paciente_modificar.getCedula());
        fechaNacimientoInput.setText(paciente_modificar.getFechaNacimiento());

        fechaNacimientoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private void RetrofitClient() {

        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public ApiPaciente getApi(){
        return retrofit.create(ApiPaciente.class);
    }

    private void createUp(){

        nombreInput = findViewById(R.id.id_nombre);
        apellidoInput = findViewById(R.id.id_apellido);
        emailInput = findViewById(R.id.id_ema);
        telofonoInput = findViewById(R.id.id_tel);
        rucInput = findViewById(R.id.id_ruc);
        cedulaInput = findViewById(R.id.id_ced);

        fechaNacimientoInput = findViewById(R.id.id_fecha);
        fechaNacimientoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        botonRegistrar = findViewById(R.id.id_registrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nombreInput.getText().toString();
                String apellido = apellidoInput.getText().toString();
                String email = emailInput.getText().toString();
                String telefono = telofonoInput.getText().toString().trim();
                String ruc = rucInput.getText().toString().trim();
                String cedula = cedulaInput.getText().toString().trim();
                String fechaNacimiento = fechaNacimientoInput.getText().toString();

                if ( nombre.isEmpty()){
                    nombreInput.setError("Requiere el nombre");
                    nombreInput.requestFocus();
                    return;
                }

                if ( email.isEmpty()){
                    emailInput.setError("Requiere el email");
                    emailInput.requestFocus();
                    return;
                }

                if ( telefono.isEmpty()){
                    telofonoInput.setError("Requiere el numero de telefono");
                    telofonoInput.requestFocus();
                    return;
                }

                if ( ruc.isEmpty()){
                    rucInput.setError("Requiere el RUC");
                    rucInput.requestFocus();
                    return;
                }

                if ( cedula.isEmpty()){
                    cedulaInput.setError("Requiere el numero de cedula");
                    cedulaInput.requestFocus();
                    return;
                }

                if ( !fechaNacimiento.isEmpty()){
                    fechaNacimiento+=" 00:00:00";
                }
                Paciente pac= new Paciente(paciente_modificar==null?null:paciente_modificar.getIdPersona(),nombre, apellido, email, telefono, ruc, cedula, fechaNacimiento);
                if(paciente_modificar==null){
                Call<Paciente> call = getApi().createPaciente(pac);


                    call.enqueue(new Callback<Paciente>() {
                        @Override
                        public void onResponse(Call<Paciente> call, Response<Paciente> response) {

                            Toast.makeText(CrearPaciente.this, "Registro con exito", Toast.LENGTH_LONG).show();

                            Intent intentNewActivity;
                            Bundle b = new Bundle();

                            intentNewActivity = new Intent(CrearPaciente.this, PacientesActivity.class);
                            intentNewActivity.putExtras(b);
                            startActivity(intentNewActivity);
                        }

                        @Override
                        public void onFailure(Call<Paciente> call, Throwable t) {
                            Toast.makeText(CrearPaciente.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    Call<Void> call = getApi().modificarPaciente(pac);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(CrearPaciente.this, "Guardado con exito", Toast.LENGTH_LONG).show();

                            Intent intentNewActivity;
                            Bundle b = new Bundle();

                            intentNewActivity = new Intent(CrearPaciente.this, PacientesActivity.class);
                            intentNewActivity.putExtras(b);
                            startActivity(intentNewActivity);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("sa","as");
                        }
                    });
                }
            }
        });

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final String selectedDate = dayOfMonth + "-" + (month+1) + "-" + year;
                fechaNacimientoInput.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
