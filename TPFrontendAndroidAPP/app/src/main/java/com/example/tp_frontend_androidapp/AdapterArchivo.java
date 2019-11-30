package com.example.tp_frontend_androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.modelos.FichaArchivo;

public class AdapterArchivo extends RecyclerView.Adapter<AdapterArchivo.AdapterArchivoHolder>{

    private RecyclerViewClickListener listener;
    FichaArchivo[] lista;


    public AdapterArchivo(FichaArchivo [] l, RecyclerViewClickListener listener){
        super();
        lista=l;
        this.listener=listener;

    }
    @NonNull
    @Override
    public AdapterArchivoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archivo_item_lista_archivo,parent,false);

        return new AdapterArchivoHolder(v, listener);
                
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArchivoHolder holder, int position) {


        holder.tvNombre.setText(lista[position].getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }



    public static class AdapterArchivoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        RecyclerViewClickListener mListener;
        TextView tvNombre;
        ImageButton eliminarBtn;
        public AdapterArchivoHolder(View view, RecyclerViewClickListener listener){
            super(view);
            tvNombre= view.findViewById(R.id.nombre_txt);
            eliminarBtn= view.findViewById(R.id.eliminarBtn);
            mListener = listener;
            eliminarBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}

