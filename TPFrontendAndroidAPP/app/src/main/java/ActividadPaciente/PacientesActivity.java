package ActividadPaciente;

import android.os.Bundle;
import android.util.Log;

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

        RecyclerViewAdaptador recly_adaptador = new RecyclerViewAdaptador(getApplicationContext(), Arrays.asList(response.getLista()));

        recyclerView.setAdapter(recly_adaptador);
    }

}
