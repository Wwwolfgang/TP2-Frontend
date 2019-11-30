package com.example.tp_frontend_androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_frontend_androidapp.modelos.Categoria;
import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.example.tp_frontend_androidapp.modelos.FichaArchivo;
import com.example.tp_frontend_androidapp.modelos.FichaClinica;
import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.SubCategoria;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ActividadPaciente.PacientesActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearFichaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FichaClinica ficha_modificar=null;
    boolean sel_subc=true;
    ArrayList<FichaArchivo> lista_archivo = new ArrayList<>();
    ArrayList<Integer> archivos_a_eliminar = new ArrayList<>();
    Paciente paciente;
    Doctor doctor;

    RecyclerView rvArchivo;
    AdapterArchivo adapter;
    Button paciente_buscar;
    Button doctor_buscar;
    Spinner categoria_spinner;
    Spinner subcategoria_spinner;
    EditText observacion_txt;
    EditText motivo_txt;
    EditText diagnostico_txt;
    TextView paciente_txt;
    TextView doctor_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ficha);

        categoria_spinner = findViewById(R.id.lista_categoria_spinner);
        subcategoria_spinner = findViewById(R.id.lista_subcategoria_spinner);
        categoria_spinner.setOnItemSelectedListener(this);
        observacion_txt = findViewById(R.id.observacion_txt);
        motivo_txt = findViewById(R.id.motivo_txt);
        diagnostico_txt = findViewById(R.id.diagnostico_txt);
        paciente_txt = findViewById(R.id.paciente_ficha_txt);
        doctor_txt = findViewById(R.id.doctor_ficha_txt);



        prepararRvArchivos();

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null && bundle.containsKey("fichaEditar")){
            ficha_modificar = (FichaClinica) bundle.getSerializable("fichaEditar");

            paciente_buscar = findViewById(R.id.buscar_paciente_button);
            doctor_buscar = findViewById(R.id.buscar_doctor_button);
            paciente_buscar.setVisibility(View.INVISIBLE);
            doctor_buscar.setVisibility(View.INVISIBLE);
            diagnostico_txt.setEnabled(false);
            motivo_txt.setEnabled(false);
            categoria_spinner.setEnabled(false);
            subcategoria_spinner.setEnabled(false);
            cargarCampos();
        }
        else{
            LinearLayout ly = findViewById(R.id.accion_ficha_lt);
            ly.removeView(findViewById(R.id.eliminar_ficha_btn));
        }

        cargarCategorias();


    }

    public void obtenerPaciente(View v){
        //TODO LLamar a la actividad Paciente, poner el resultado en this.paciente
        Intent i = new Intent(this, PacientesActivity.class);
        i.putExtra("busqueda","so");
        startActivityForResult(i, 50);
    }

    public void obtenerDoctor(View v){
        //TODO Llamar a la actividad Doctor, poner el resultado en this.doctor
        Intent i = new Intent(this, ListaDoctorActivity.class);
        startActivityForResult(i, 40);
    }

    public void eliminarFicha(View v){
        Call<Integer> callEliminarFicha = Servicios.getFichaClinicaService().cancelarFicha(ficha_modificar.getIdFichaClinica());
        callEliminarFicha.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(CrearFichaActivity.this,"Ficha eliminada",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void cargarCampos(){
        observacion_txt.setText(ficha_modificar.getObservacion());
        motivo_txt.setText(ficha_modificar.getMotivoConsulta());
        diagnostico_txt.setText(ficha_modificar.getDiagnostico());
        doctor_txt.setText(ficha_modificar.getIdEmpleado().getNombre());
        paciente_txt.setText(ficha_modificar.getIdCliente().getNombre());
        Call<Lista<FichaArchivo>> callArchivos = Servicios.getFichaClinicaService().obtenerArchivos(ficha_modificar.getIdFichaClinica());
        callArchivos.enqueue(new Callback<Lista<FichaArchivo>>() {
            @Override
            public void onResponse(Call<Lista<FichaArchivo>> call, Response<Lista<FichaArchivo>> response) {
                FichaArchivo[] lista=response.body().getLista();
                for(FichaArchivo archivo:lista){
                    lista_archivo.add(archivo);
                }

                adapter.lista = lista_archivo.toArray(new FichaArchivo[lista_archivo.size()]);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Lista<FichaArchivo>> call, Throwable t) {

            }
        });
    }


    private void prepararRvArchivos(){
        rvArchivo=findViewById(R.id.rvArchivo);
        LinearLayoutManager layRec =  new LinearLayoutManager(this);
        rvArchivo.setLayoutManager(layRec);
        rvArchivo.setHasFixedSize(true);
        adapter = new AdapterArchivo(lista_archivo.toArray(new FichaArchivo[lista_archivo.size()]), new RecyclerViewClickListener() {
            @Override
            /**
             * Metodo para agregar al arraylist de archivos a eliminar el archivo seleccionado
             */
            public void onClick(View view, int position) {
                Integer id=lista_archivo.remove(position).getIdFichaArchivo();
                adapter.lista = lista_archivo.toArray(new FichaArchivo[lista_archivo.size()]);
                adapter.notifyDataSetChanged();
                if(id != null){
                    archivos_a_eliminar.add(id);
                }
            }
        });
        rvArchivo.setAdapter(adapter);
    }

    private void cargarCategorias(){
        Call<Lista<Categoria>> callCategoria=Servicios.getCategoriaService().obtenerCategorias("descripcion","asc");
        callCategoria.enqueue(new Callback<Lista<Categoria>>() {
            @Override
            public void onResponse(Call<Lista<Categoria>> call, Response<Lista<Categoria>> response) {
                Categoria [] items = response.body().getLista();

                ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(CrearFichaActivity.this, android.R.layout.simple_list_item_1,items);

                categoria_spinner.setAdapter(adapter);
                if(ficha_modificar!=null) {
                    int i = 0;
                    for (Categoria cat : items) {

                        if (cat.getIdCategoria().compareTo(ficha_modificar.getIdTipoProducto().getIdCategoria().getIdCategoria()) == 0) {
                            categoria_spinner.setSelection(i);

                            break;
                        }
                        i++;
                    }
                }
            }

            @Override
            public void onFailure(Call<Lista<Categoria>> call, Throwable t) {
                Log.w("warning",t.getCause().toString());
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
                SubCategoria [] items = response.body().getLista();

                ArrayAdapter<SubCategoria> adapter = new ArrayAdapter<>(CrearFichaActivity.this, android.R.layout.simple_list_item_1,items);

                subcategoria_spinner.setAdapter(adapter);

                if(sel_subc && ficha_modificar !=null) {
                    int i = 0;
                    for (SubCategoria scat : items) {

                        if (scat.getIdTipoProducto().compareTo(ficha_modificar.getIdTipoProducto().getIdTipoProducto()) == 0) {
                            subcategoria_spinner.setSelection(i);
                            break;
                        }
                        i++;
                    }
                }
                sel_subc = false;
            }

            @Override
            public void onFailure(Call<Lista<SubCategoria>> call, Throwable t) {
                Log.w("warning",t.getCause().toString());
            }
        });
    }


    public void seleccionarArchivo(View v){
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");

        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,1997);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 1997://CASO PARA LEER ARCHIVO
                    //data.getData returns the content URI for the selected Image
                    if (data != null) {

                        Uri selectedImage = data.getData();
                        //File file = new File(selectedImage.getPath());

                        FichaArchivo archivo =  new FichaArchivo();
                        archivo.setDebe_agregarse(true);
                        archivo.setNombre(getFileName(selectedImage));
                        archivo.uri=selectedImage;
                        lista_archivo.add(archivo);
                        adapter.lista = lista_archivo.toArray(new FichaArchivo[lista_archivo.size()]);
                        adapter.notifyDataSetChanged();

                    }

                    break;
                case 40:
                    doctor=new Doctor();
                    doctor.setIdPersona(data.getIntExtra("doctorId",0));
                    doctor_txt.setText(data.getStringExtra("doctorNombre"));
                    break;
                case 50:
                    paciente=new Paciente();
                    paciente.setIdPersona(data.getIntExtra("pacienteId",0));
                    paciente_txt.setText(data.getStringExtra("pacienteNombre"));
                    break;
            }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.lista_categoria_spinner && position > 0)
        {
            //do this

            cargarSubCategorias(((Categoria)parent.getItemAtPosition(position)).getIdCategoria());


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void guardar(View v){
        if(isEmpty(motivo_txt)){
            setError(motivo_txt,"Campo requerido");
            return;
        }
        if(isEmpty(diagnostico_txt)){
            setError(motivo_txt,"Campo requerido");
            return;
        }

        SubCategoria scat=(SubCategoria)subcategoria_spinner.getSelectedItem();
        if(scat == null){
            Toast.makeText(this,"Seleccione al menos una subcategoria",Toast.LENGTH_SHORT).show();
            return;

        }

        if(paciente == null){
            Toast.makeText(this,"Seleccione un paciente",Toast.LENGTH_SHORT).show();
            return;

        }
        if(doctor == null){

            Toast.makeText(this,"Seleccione un doctor",Toast.LENGTH_SHORT).show();
            return;
        }

        FichaClinica ficha=new FichaClinica();

        ficha.setIdCliente(paciente);
        ficha.setIdEmpleado(doctor);
        ficha.setMotivoConsulta(motivo_txt.getText().toString());
        ficha.setDiagnostico(diagnostico_txt.getText().toString());
        ficha.setObservacion(observacion_txt.getText().toString());
        ficha.setIdTipoProducto(scat);

        //SOLO SE PUEDE MODIFICAR LA OBSERVACION

        if(ficha_modificar != null){
            ficha = new FichaClinica();
            ficha.setIdFichaClinica(ficha_modificar.getIdFichaClinica());
            ficha.setObservacion(observacion_txt.getText().toString());

        }
        Call<FichaClinica> callCategoria=ficha_modificar==null?Servicios.getFichaClinicaService().cargarFicha(ficha,"pedro")
                :Servicios.getFichaClinicaService().modificarFicha(ficha,"pedro");
        callCategoria.enqueue(new Callback<FichaClinica>() {
            @Override
            public void onResponse(Call<FichaClinica> call, Response<FichaClinica> response) {
                Integer id = response.body().getIdFichaClinica();
                Log.d("idFicha",id.toString());
                for(FichaArchivo archivo:lista_archivo){
                    if(archivo.isDebe_agregarse()) {
                        try {
                            InputStream iStream = null;
                            iStream = getContentResolver().openInputStream(archivo.uri);
                            byte[] inputData = getBytes(iStream);
                            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), inputData);
                            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), archivo.getNombre());
                            RequestBody id1 = RequestBody.create(MediaType.parse("text/plain"), id.toString());
                            Servicios.getFichaClinicaService().cargarArchivo(fbody, name, id1).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Log.d("idArchivo", response.body());
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    int a = 2;
                                }
                            });
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }


                }
                for(int el: archivos_a_eliminar){
                    //TODO: Eliminar los archivos
                    Call<Integer> callEliminarArchivo = Servicios.getFichaClinicaService().eliminarArchivo(el);
                    callEliminarArchivo.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {

                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });

                }
                Toast.makeText(CrearFichaActivity.this,"Agregado Correctamente"+id,Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<FichaClinica> call, Throwable t) {
                Log.w("warning",t.getCause().toString());
            }
        });

    }

    /**
     * Convertir un inputstream a bytes
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    /**
     * Obtener el nombre de un archivo a apartir de su uri
     * @param uri
     * @return
     */
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    public static void setError(EditText editText, String errorString) {

        editText.setError(errorString);

    }

    public static void clearError(EditText editText) {

        editText.setError(null);

    }
}
