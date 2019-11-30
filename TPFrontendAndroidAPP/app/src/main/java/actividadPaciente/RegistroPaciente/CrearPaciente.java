package actividadPaciente.RegistroPaciente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tp_frontend_androidapp.R;

public class CrearPaciente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        getSupportActionBar().setTitle("Registro Paciente");
    }
}
