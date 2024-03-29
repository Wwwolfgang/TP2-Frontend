package actividadPaciente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.MenuPrincipalActivity;
import com.example.tp_frontend_androidapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import actividadPaciente.Adaptador.RecyclerViewAdaptador;
import actividadPaciente.DetallePaciente.Detalle;
import actividadPaciente.Modelo.ListaPaciente;
import actividadPaciente.Modelo.Paciente;
import actividadPaciente.RegistroPaciente.CrearPaciente;
import actividadPaciente.Servicio.ApiPaciente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PacientesActivity extends AppCompatActivity {

    private final String url = "http://gy7228.myfoscam.org:8080/stock-pwfe/";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerViewAdaptador recyclerViewAdaptador;
    private String busquedaId = "pacienteId";
    private String busquedaNombre = "pacienteNombre";
    private String ejemplo;
    private FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = findViewById(R.id.crear_paciente_float);
        getSupportActionBar().setTitle("Pacientes");
        if(getIntent().hasExtra("busqueda")){
            btn.hide();
            if(getIntent().getStringExtra("busqueda").compareTo("paciente")!=0){
                getSupportActionBar().setTitle("Doctores");
                busquedaId = "doctorId";
                busquedaNombre = "doctorNombre";
                ejemplo = "{\"soloUsuariosDelSistema\":true}";
            }
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        extraerListaPacientes();

    }

    private void extraerListaPacientes(){

        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        ApiPaciente getpacient = retrofit.create(ApiPaciente.class);


        Call<ListaPaciente<Paciente>> listcall = getpacient.getDatos(ejemplo);

        listcall.enqueue(new Callback<ListaPaciente<Paciente>>() {
            @Override
            public void onResponse(Call<ListaPaciente<Paciente>> call, Response<ListaPaciente<Paciente>> response) {

                mostrarReclyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ListaPaciente<Paciente>> call, Throwable t) {
                Log.d("sa",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!getIntent().hasExtra("busqueda")){
            startActivity(new Intent(this, MenuPrincipalActivity.class));
        }

    }

    private void mostrarReclyclerView(ListaPaciente<Paciente> response) {

        recyclerView = findViewById(R.id.recyclerviewid);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAdaptador = new RecyclerViewAdaptador(Arrays.asList(response.getLista()));

        if(getIntent().hasExtra("busqueda")){
            recyclerViewAdaptador.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = getIntent();
                    i.putExtra(busquedaId, recyclerViewAdaptador.getPaciente(recyclerView.getChildAdapterPosition(v)).getIdPersona());
                    i.putExtra(busquedaNombre, recyclerViewAdaptador.getPaciente(recyclerView.getChildAdapterPosition(v)).getNombre());
                    setResult(RESULT_OK, i);
                    finish();
                }
            });
        }
        else{

            recyclerViewAdaptador.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent j = new Intent(PacientesActivity.this, Detalle.class);
                    Paciente pacient = recyclerViewAdaptador.getPaciente(recyclerView.getChildAdapterPosition(v));
                    j.putExtra("paciente", pacient);
                    startActivity(j);
                }
            });
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_buscar_menu, menu);

        MenuItem buscarPacient = menu.findItem(R.id.accion_buscar);
        SearchView searchView = (SearchView) buscarPacient.getActionView();
        searchView.setQueryHint("Buscar...");

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recyclerViewAdaptador.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    public void btn_crear_paciente(View view) {

        Intent intentNewActivity;
        Bundle b = new Bundle();

        intentNewActivity = new Intent(PacientesActivity.this, CrearPaciente.class);
        intentNewActivity.putExtras(b);
        startActivity(intentNewActivity);

    }
}
