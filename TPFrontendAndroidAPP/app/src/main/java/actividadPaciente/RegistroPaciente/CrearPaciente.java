package actividadPaciente.RegistroPaciente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.R;
import com.google.android.material.textfield.TextInputEditText;

import actividadPaciente.Modelo.Paciente;
import actividadPaciente.PacientesActivity;
import actividadPaciente.Servicio.ApiPaciente;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        getSupportActionBar().setTitle("Registro Paciente");

        RetrofitClient();
        createUp();
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
        emailInput = findViewById(R.id.id_nombre);
        telofonoInput = findViewById(R.id.id_tel);
        rucInput = findViewById(R.id.id_ruc);
        cedulaInput = findViewById(R.id.id_ced);
        fechaNacimientoInput = findViewById(R.id.id_fecha);

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
                }

                if ( telefono.isEmpty()){
                    telofonoInput.setError("Requiere el numero de telefono");
                    telofonoInput.requestFocus();
                }

                if ( ruc.isEmpty()){
                    rucInput.setError("Requiere el RUC");
                    rucInput.requestFocus();
                }

                if ( cedula.isEmpty()){
                    cedulaInput.setError("Requiere el numero de cedula");
                    cedulaInput.requestFocus();
                }

                if ( fechaNacimiento.isEmpty()){
                    fechaNacimientoInput.setError("Requiere la fecha de Nacimiento");
                    fechaNacimientoInput.requestFocus();
                }
                Paciente pac= new Paciente(null,nombre, apellido, email, telefono, ruc, cedula, fechaNacimiento);
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
        });

    }

}
