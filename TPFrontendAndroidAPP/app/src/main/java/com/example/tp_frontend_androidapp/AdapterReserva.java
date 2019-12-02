package com.example.tp_frontend_androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.modelos.Paciente;
import com.example.tp_frontend_androidapp.modelos.Reserva;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.AdapterReservaHolder> implements View.OnClickListener{
    private View.OnClickListener listener;

    Reserva[] lista;
    Paciente paciente;
    Paciente doctor;

    public AdapterReserva(Reserva[] lista){super(); this.lista = lista;}

    @NonNull
    @Override
    public AdapterReservaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserva_item_lista,parent,false);
        v.setOnClickListener(listener);
        AdapterReservaHolder ar = new AdapterReservaHolder(v);
        return  ar;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReservaHolder holder, int position) {
        holder.tvFecha.setText("Fecha:\t"+lista[position].getFecha());

        holder.tvPaciente.setText("Paciente:\t"+lista[position].getIdCliente().getNombre());

        holder.tvDoctor.setText("Doctor:\t"+lista[position].getIdEmpleado().getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }



    public static class AdapterReservaHolder extends RecyclerView.ViewHolder {

        TextView tvFecha;
        TextView tvPaciente;
        TextView tvDoctor;
        public AdapterReservaHolder(View v){
            super(v);
            tvPaciente = v.findViewById(R.id.txtPacienteItem);
            tvDoctor = v.findViewById(R.id.txtDoctorItem);
            tvFecha = v.findViewById(R.id.txtFechaItem);

        }
    }
}
