package com.example.tp_frontend_androidapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.Reserva;
import com.google.gson.Gson;

import java.util.Calendar;

import ActividadPaciente.PacientesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
    RecyclerView rvReservas;

    AdapterReserva adapter;
    //
    Reserva[] lista= new Reserva[0];

    String fechaDesde;
    String fechaHasta;
    Paciente paciente;
    Paciente doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        //Widget
        etFechaD = findViewById(R.id.et_mostrar_fecha_d_pickerReserva);
        etFechaH = findViewById(R.id.et_mostrar_fecha_h_pickerReserva);
        rvReservas = findViewById(R.id.rvListaReserva);
        LinearLayoutManager layRec =  new LinearLayoutManager(this);
        rvReservas.setLayoutManager(layRec);
        rvReservas.setHasFixedSize(true);
        adapter=new AdapterReserva(lista);
        rvReservas.setAdapter(adapter);
        fechaActual(anio,mes,dia);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    public void irAgregarReserva(View v){
        Intent intent = new Intent(this, CrearReservaActivity.class);
        startActivity(intent);
    }

    public void obtenerPaciente(View v){
        //TODO LLamar a la actividad Paciente, poner el resultado en this.paciente
        Intent intent = new Intent(this, PacientesActivity.class);
        intent.putExtra("busqueda",0);
        startActivityForResult(intent,40);
    }

    public void obtenerDoctor(View v){
        //TODO Llamar a la actividad Doctor, poner el resultado en this.doctor
        Intent intent = new Intent(this, ListaDoctorActivity.class);
        startActivityForResult(intent,50);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case 40:
                    paciente = new Paciente();
                    paciente.setIdPersona(data.getIntExtra("pacienteId",0));
                    break;
                case 50:
                    doctor = new Paciente();
                    doctor.setIdPersona(data.getIntExtra("doctorId",0));
                    break;
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void cargarLista(){
        Reserva reserva = null;
        if(fechaDesde != null || fechaHasta != null || paciente != null || doctor != null){
            reserva = new Reserva();
            reserva.setFechaDesdeCadena(fechaDesde);
            reserva.setFechaHastaCadena(fechaHasta);
            reserva.setIdCliente(paciente);
            reserva.setIdEmpleado(doctor);

        }
        Gson gson = new Gson();
        String ejemplo = null;
        if(reserva !=null){
            ejemplo = gson.toJson(reserva);
        }
        Call<Lista<Reserva>> callReserva = Servicios.getReservaService().obtenerReservas("fecha",ejemplo);
        callReserva.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                cargarLista(response.body().getLista());
            }

            @Override
            public void onFailure(Call<Lista<Reserva>> call, Throwable t) {
                Log.w("warning",t.getCause().toString());
            }
        });

    }

    private void cargarLista(final Reserva[] reservas){

        adapter.lista = reservas;
        adapter.notifyDataSetChanged();
        adapter.setListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentNewActivity = new Intent(ReservaActivity.this, EditarAgregarReservaActivity.class);
                Bundle b = new Bundle();

                b.putSerializable("reservaEditar",adapter.lista[rvReservas.getChildAdapterPosition(view)]);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);

            }
        });
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

    public void fechaActual(int year,int month,int dayOfMonth){
        final int mesActual = month + 1;
        //Formateo el día obtenido: antepone el 0 si son menores de 10
        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
        //Formateo el mes obtenido: antepone el 0 si son menores de 10
        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
        //Muestro la fecha con el formato deseado
        fechaDesde = year + mesFormateado + diaFormateado;
        fechaHasta = year + mesFormateado + diaFormateado;
        etFechaD.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
        etFechaH.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
        cargarLista();
    }
}
