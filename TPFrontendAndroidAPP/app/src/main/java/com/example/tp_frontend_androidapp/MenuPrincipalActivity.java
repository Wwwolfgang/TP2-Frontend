package com.example.tp_frontend_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ActividadPaciente.PacientesActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void abrir(View v){
        String tag=v.getTag().toString();
        Intent intentNewActivity;
        Bundle b = new Bundle();
        //b.putString("usuario",campoNombreUsuario.getText().toString());
        switch (tag){
            case "Reservas":
                intentNewActivity = new Intent(MenuPrincipalActivity.this, CrearReservaActivity.class);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);
                break;
            case "Pacientes":
                intentNewActivity = new Intent(MenuPrincipalActivity.this, PacientesActivity.class);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);
                break;
            case "Fichas":
                intentNewActivity = new Intent(MenuPrincipalActivity.this, ListaFichaActivity.class);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);
                break;
            default:
                break;
        }





    }
}
