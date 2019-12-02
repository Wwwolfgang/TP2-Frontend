package com.example.tp_frontend_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tp_frontend_androidapp.modelos.Categoria;
import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.example.tp_frontend_androidapp.modelos.FichaClinica;
import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.SubCategoria;
import com.google.gson.Gson;

import java.util.Calendar;

import actividadPaciente.PacientesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFichaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFechaD;
    EditText etFechaH;
    Spinner categoriaSpiner;
    Spinner subcategoriaSpiner;
    RecyclerView rvFichas;
    AdapterFichaClinica adapter;
    //
    FichaClinica[] lista= new FichaClinica[0];
    String fechaDesde;
    String fechaHasta;
    Paciente paciente;
    Doctor doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ficha);
        //Widget
        etFechaD = findViewById(R.id.et_mostrar_fecha_d_picker);
        etFechaH = findViewById(R.id.et_mostrar_fecha_h_picker);
        subcategoriaSpiner =  findViewById(R.id.lista_subcategoria_spinner_1);
        categoriaSpiner =  findViewById(R.id.lista_categoria_spinner_1);
        categoriaSpiner.setOnItemSelectedListener(this);
        subcategoriaSpiner.setOnItemSelectedListener(this);
        rvFichas = findViewById(R.id.rvListaFicha);
        LinearLayoutManager layRec =  new LinearLayoutManager(this);
        rvFichas.setLayoutManager(layRec);
        rvFichas.setHasFixedSize(true);
        adapter=new AdapterFichaClinica(lista);
        rvFichas.setAdapter(adapter);


        cargarCategorias();


    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    public void obtenerPaciente(View v){
        //TODO LLamar a la actividad Paciente, poner el resultado en this.paciente
        Intent i = new Intent(this, PacientesActivity.class);
        i.putExtra("busqueda","paciente");
        startActivityForResult(i, 50);
    }

    public void obtenerDoctor(View v){
        //TODO Llamar a la actividad Doctor, poner el resultado en this.doctor
        Intent i = new Intent(this, PacientesActivity.class);
        i.putExtra("busqueda","doctor");
        startActivityForResult(i, 40);
    }

    private void cargarCategorias(){
        Call<Lista<Categoria>> callCategoria=Servicios.getCategoriaService().obtenerCategorias("descripcion","asc");
        callCategoria.enqueue(new Callback<Lista<Categoria>>() {
            @Override
            public void onResponse(Call<Lista<Categoria>> call, Response<Lista<Categoria>> response) {
                Categoria [] items = new Categoria[0];
                if (response.body() != null) {
                    items = response.body().getLista();
                }

                ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(ListaFichaActivity.this, android.R.layout.simple_list_item_1,items);

                categoriaSpiner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Lista<Categoria>> call, Throwable t) {
                Log.w("warning",t.getCause());
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".
        if (resultCode == RESULT_OK) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            switch (requestCode) {
                case 40:
                    doctor=new Doctor();
                    doctor.setIdPersona(data.getIntExtra("doctorId",0));

                    break;
                case 50:
                    paciente=new Paciente();
                    paciente.setIdPersona(data.getIntExtra("pacienteId",0));

                    break;

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.lista_categoria_spinner_1 && position > 0)
        {
            //do this

            cargarSubCategorias(((Categoria)parent.getItemAtPosition(position)).getIdCategoria());


        }
        if(parent.getId() == R.id.lista_subcategoria_spinner_1)
        {
            //do this

            cargarLista();


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void cargarLista(){
        FichaClinica ficha = null;
        if(fechaDesde != null || fechaHasta != null || subcategoriaSpiner.getSelectedItem() != null || paciente != null || doctor != null){
            ficha = new FichaClinica();
            ficha.setFechaDesdeCadena(fechaDesde);
            ficha.setFechaHastaCadena(fechaHasta);
            ficha.setIdCliente(paciente);
            ficha.setIdEmpleado(doctor);
            if(subcategoriaSpiner.getSelectedItem()!=null){

                ficha.setIdTipoProducto((SubCategoria)subcategoriaSpiner.getSelectedItem());
            }
        }
        Gson gson = new Gson();
        String ejemplo = null;
        if(ficha !=null){
            ejemplo = gson.toJson(ficha);
        }
        Call<Lista<FichaClinica>> callFichas = Servicios.getFichaClinicaService().obtenerFichas("fechaHoraCadena",ejemplo);
        callFichas.enqueue(new Callback<Lista<FichaClinica>>() {
            @Override
            public void onResponse(Call<Lista<FichaClinica>> call, Response<Lista<FichaClinica>> response) {
                if (response.body() != null) {
                    cargarLista(response.body().getLista());
                }
            }

            @Override
            public void onFailure(Call<Lista<FichaClinica>> call, Throwable t) {
                Log.w("warning",t.getCause());
            }
        });

    }

    private void cargarSubCategorias(int id){
        Categoria cat = new Categoria();
        cat.setIdCategoria(id);
        SubCategoria sub = new SubCategoria();
        sub.setIdCategoria(cat);
        Gson gson = new Gson();
        String ejemplo = gson.toJson(sub);
        Call<Lista<SubCategoria>> callCategoria=Servicios.getSubCategoriaService().obtenerSubCategorias("descripcion","asc", ejemplo);
        callCategoria.enqueue(new Callback<Lista<SubCategoria>>() {
            @Override
            public void onResponse(Call<Lista<SubCategoria>> call, Response<Lista<SubCategoria>> response) {
                SubCategoria [] items = new SubCategoria[0];
                if (response.body() != null) {
                    items = response.body().getLista();
                }

                ArrayAdapter<SubCategoria> adapter = new ArrayAdapter<>(ListaFichaActivity.this, android.R.layout.simple_list_item_1,items);

                subcategoriaSpiner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Lista<SubCategoria>> call, Throwable t) {
                Log.w("warning",t.getCause());
            }
        });
    }

    private void cargarLista(final FichaClinica[] fichas){


        adapter.lista = fichas;
        adapter.setListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentNewActivity = new Intent(ListaFichaActivity.this, CrearFichaActivity.class);
                Bundle b = new Bundle();
                Log.d("posicion",rvFichas.getChildAdapterPosition(view)+"");
                Log.d("fichaId",adapter.lista[rvFichas.getChildAdapterPosition(view)].getIdFichaClinica().toString());
                b.putSerializable("fichaEditar",adapter.lista[rvFichas.getChildAdapterPosition(view)]);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);

            }
        });
        adapter.notifyDataSetChanged();

    }

    public void obtenerFechaDesde(View v){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFechaD.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                fechaDesde = year + mesFormateado + diaFormateado;
                cargarLista();

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    public void obtenerFechaHasta(View v){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFechaH.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                fechaHasta = year + mesFormateado + diaFormateado;
                cargarLista();

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    public void irAgregarFicha(View v){
        Intent intentNewActivity;
        Bundle b = new Bundle();
        intentNewActivity = new Intent(ListaFichaActivity.this, CrearFichaActivity.class);
        intentNewActivity.putExtras(b);
        startActivity(intentNewActivity);
    }
}
