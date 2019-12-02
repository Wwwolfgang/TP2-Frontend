package com.example.tp_frontend_androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if(campoNombreUsuario.getText().toString().trim().isEmpty()){
            campoNombreUsuario.setError("Campo requerido");
            return;
        }
        Doctor doc = new Doctor();
        doc.setSoloUsuariosDelSistema(true);
        doc.setUsuarioLogin(campoNombreUsuario.getText().toString());
        Gson gson = new Gson();
        Call<Lista<Doctor>> callUser = Servicios.getDoctorService().obtenerDoctores(null,null,gson.toJson(doc),null);
        callUser.enqueue(new Callback<Lista<Doctor>>() {
            @Override
            public void onResponse(Call<Lista<Doctor>> call, Response<Lista<Doctor>> response) {

                if(response.body().getTotalDatos().compareTo(0L)!=0){
                    Intent intentNewActivity = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                    Bundle b = new Bundle();
                    b.putString("usuario",campoNombreUsuario.getText().toString());
                    MainActivity.this.getSharedPreferences("prueba", Context.MODE_PRIVATE).edit().putString("nombre",campoNombreUsuario.getText().toString()).commit();
                    intentNewActivity.putExtras(b);
                    startActivity(intentNewActivity);
                }
                else{
                    Toast.makeText(MainActivity.this,"Usuario no coincide!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lista<Doctor>> call, Throwable t) {

            }
        });


    }
}
