package actividadPaciente.DetallePaciente;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.R;

import actividadPaciente.Modelo.Paciente;

public class Detalle extends AppCompatActivity {

    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);



        mostrarDetalles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalles, menu);
        return true;
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
}
