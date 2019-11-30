package com.example.tp_frontend_androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.modelos.Doctor;

public class AdapterDoctor extends RecyclerView.Adapter<AdapterDoctor.AdapterDoctorHolder>  {

    private View.OnClickListener listener;
    Doctor[] lista;


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public AdapterDoctor(Doctor [] l){
        super();
        lista=l;
    }
    @NonNull
    @Override
    public AdapterDoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archivo_item_lista_doctor,parent,false);
        v.setOnClickListener(listener);
        return new AdapterDoctorHolder(v);
                
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDoctorHolder holder, int position) {
        holder.tvNombre.setText(lista[position].getNombre());
        holder.tvIdDoctor.setText(lista[position].getIdPersona().toString());


    }

    @Override
    public int getItemCount() {
        return lista.length;
    }



    public static class AdapterDoctorHolder extends RecyclerView.ViewHolder{
        TextView tvNombre;
        TextView tvIdDoctor;


        public AdapterDoctorHolder(View view){
            super(view);

            tvNombre = view.findViewById(R.id.nombre_doctor_txt);
            tvIdDoctor = view.findViewById(R.id.id_doctor_txt);

        }
    }
}
