package com.example.tp_frontend_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText campoNombreUsuario;
    EditText campoPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNombreUsuario=findViewById(R.id.txtNombreUsuario);
        campoPassword=findViewById(R.id.txtPassword);
    }

    public void ingresar(View view) {
        if (campoNombreUsuario.getText().toString().equals("admin")
                && campoPassword.getText().toString().equals("123")) {

            Intent intentNewActivity = new Intent(MainActivity.this, MenuPrincipalActivity.class);
            Bundle b = new Bundle();
            b.putString("usuario",campoNombreUsuario.getText().toString());
            intentNewActivity.putExtras(b);
            startActivity(intentNewActivity);


        }

    }
}
