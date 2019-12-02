package actividadPaciente.DetallePaciente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.R;

import actividadPaciente.Modelo.Paciente;
import actividadPaciente.PacientesActivity;
import actividadPaciente.RegistroPaciente.CrearPaciente;
import actividadPaciente.Servicio.ApiPaciente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detalle extends AppCompatActivity {

    private Paciente paciente;

    private final String url = "http://gy7228.myfoscam.org:8080/stock-pwfe/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        RetrofitClient();


        mostrarDetalles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalles, menu);
        return true;
    }

    private void RetrofitClient() {

        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public ApiPaciente getApi(){
        return retrofit.create(ApiPaciente.class);
    }

    private void mostrarDetalles(){
        paciente = (Paciente) this.getIntent().getSerializableExtra("paciente");

        TextView nombre = findViewById(R.id.id_name);
        TextView apellido = findViewById(R.id.id_ape);
        TextView email = findViewById(R.id.id_email);
        TextView telefono = findViewById(R.id.id_telefono);
        TextView ruc = findViewById(R.id.id_RUC);
        TextView cedula = findViewById(R.id.id_cedula);
        TextView fecha_nac = findViewById(R.id.id_fechaNacimiento);

        nombre.setText("Nombre: " + paciente.getNombre());
        apellido.setText("Apellido: " + paciente.getApellido());
        email.setText("E-mail: " + paciente.getEmail());
        telefono.setText("Teléfono: " + paciente.getTelefono());
        ruc.setText("RUC: " + paciente.getRuc());
        fecha_nac.setText("Fecha de Nacimiento: " + paciente.getFechaNacimiento());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.id_modificar:
                Intent intent = new Intent(this, CrearPaciente.class);
                intent.putExtra("paciente",paciente);
                startActivity(intent);
                finish();
                return true;
            case R.id.id_eliminar:
                Call<Void> call = getApi().eliminarPaciente(paciente.getIdPersona());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200){
                            Intent intent1 = new Intent(Detalle.this, PacientesActivity.class);
                            startActivity(intent1);
                            Toast.makeText(Detalle.this,"Paciente eliminado!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(Detalle.this,"No se puede eliminar",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Detalle.this,"No se puede eliminar",Toast.LENGTH_SHORT).show();
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
