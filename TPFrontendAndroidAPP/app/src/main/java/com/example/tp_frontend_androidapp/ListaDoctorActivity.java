package com.example.tp_frontend_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.example.tp_frontend_androidapp.modelos.FichaClinica;
import com.example.tp_frontend_androidapp.modelos.SubCategoria;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaDoctorActivity extends AppCompatActivity {

    RecyclerView rvDoctores;
    AdapterDoctor adapter;
    EditText nombre_doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_doctor);
        rvDoctores = findViewById(R.id.rvListaDoctor);
        nombre_doctor = findViewById(R.id.buscar_doctor_txt);
        LinearLayoutManager layRec =  new LinearLayoutManager(this);
        rvDoctores.setLayoutManager(layRec);
        rvDoctores.setHasFixedSize(true);
        adapter=new AdapterDoctor(new Doctor[0]);
        rvDoctores.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    public void buscarDoctor(View v){
        cargarLista();
    }
    private void cargarLista(){
        Doctor doctor = null;
        String like = null;
        String nombre = nombre_doctor.getText().toString().trim();

        doctor = new Doctor();
        doctor.setSoloUsuariosDelSistema(true);
        if( !nombre.isEmpty()){
            doctor.setNombre(nombre);
            like = "S";

        }
        Gson gson = new Gson();
        String ejemplo = gson.toJson(doctor);

        Call<Lista<Doctor>> callDoctores = Servicios.getDoctorService().obtenerDoctores("nombre","asc",ejemplo,like);
        callDoctores.enqueue(new Callback<Lista<Doctor>>() {
            @Override
            public void onResponse(Call<Lista<Doctor>> call, Response<Lista<Doctor>> response) {
                cargarLista(response.body().getLista());
            }

            @Override
            public void onFailure(Call<Lista<Doctor>> call, Throwable t) {
                Log.w("warning",t.getCause().toString());
            }
        });

    }


    private void cargarLista(final Doctor[] fichas){

        adapter.lista = fichas;
        adapter.notifyDataSetChanged();
        adapter.setListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                i.putExtra("doctorId", adapter.lista[rvDoctores.getChildAdapterPosition(view)].getIdPersona());
                i.putExtra("doctorNombre", adapter.lista[rvDoctores.getChildAdapterPosition(view)].getNombre());
                setResult(RESULT_OK, i);
                finish();

            }
        });
    }
}
