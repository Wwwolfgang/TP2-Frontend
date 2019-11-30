package com.example.tp_frontend_androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.modelos.FichaClinica;

public class AdapterFichaClinica extends RecyclerView.Adapter<AdapterFichaClinica.AdapterFichaClinicaHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    FichaClinica[] lista;


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public AdapterFichaClinica(FichaClinica [] l){
        super();
        lista=l;
    }
    @NonNull
    @Override
    public AdapterFichaClinicaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archivo_item_lista_ficha,parent,false);
        v.setOnClickListener(listener);
        return new AdapterFichaClinicaHolder(v);
                
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFichaClinicaHolder holder, int position) {
        String paciente = lista[position].getIdEmpleado().getNombre();
        String doctor = lista[position].getIdCliente().getNombre();
        holder.tvFecha.setText("FECHA:" + lista[position].getFechaHora().substring(0,10));
        holder.tvIdFicha.setText("ID:" + lista[position].getIdFichaClinica().toString());
        holder.tvDoctor.setText("DOCTOR:" + (doctor==null?"NO":doctor));
        holder.tvPaciente.setText("PACIENTE:" + (paciente==null?"NO":paciente));


    }

    @Override
    public int getItemCount() {
        return lista.length;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    public static class AdapterFichaClinicaHolder extends RecyclerView.ViewHolder{
        TextView tvFecha;
        TextView tvIdFicha;
        TextView tvPaciente;
        TextView tvDoctor;


        public AdapterFichaClinicaHolder(View view){
            super(view);
            tvFecha= view.findViewById(R.id.fecha_clinica_txt);
            tvIdFicha= view.findViewById(R.id.id_clinica_txt);
            tvPaciente= view.findViewById(R.id.paciente_clinica_txt);
            tvDoctor= view.findViewById(R.id.doctor_clinica_txt);

        }
    }
}
