package ActividadPaciente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp_frontend_androidapp.R;

import java.util.Arrays;

import ActividadPaciente.Adaptador.RecyclerViewAdaptador;
import ActividadPaciente.Modelo.ListaPaciente;
import ActividadPaciente.Modelo.Paciente;
import ActividadPaciente.Servicio.GetPaciente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PacientesActivity extends AppCompatActivity {

    private final String url = "http://181.123.253.74:8080/stock-pwfe/";
    private Retrofit retrofit;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPaciente getpacient = retrofit.create(GetPaciente.class);

        Call<ListaPaciente<Paciente>> listcall = getpacient.getDatos();

        listcall.enqueue(new Callback<ListaPaciente<Paciente>>() {
            @Override
            public void onResponse(Call<ListaPaciente<Paciente>> call, Response<ListaPaciente<Paciente>> response) {

                mostrar(response.body());
            }

            @Override
            public void onFailure(Call<ListaPaciente<Paciente>> call, Throwable t) {
                Log.d("sa",t.getMessage());
            }
        });
    }

    private void mostrar(ListaPaciente<Paciente> response) {

        final RecyclerViewAdaptador recly_adaptador = new RecyclerViewAdaptador(getApplicationContext(), Arrays.asList(response.getLista()));
        if(getIntent().hasExtra("busqueda")){
            recly_adaptador.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = getIntent();
                    i.putExtra("pacienteId", recly_adaptador.getPaciente(recyclerView.getChildAdapterPosition(v)).getIdPersona());
                    i.putExtra("pacienteNombre", recly_adaptador.getPaciente(recyclerView.getChildAdapterPosition(v)).getNombre());
                    setResult(RESULT_OK, i);
                    finish();
                }
            });
        }
        else{
            //TODO: ACA HAY QUE PONER EL LISTENER PARA IR A EDITAR
        }

        recyclerView.setAdapter(recly_adaptador);
    }

}
