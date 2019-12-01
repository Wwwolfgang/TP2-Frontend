package actividadPaciente.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import actividadPaciente.Modelo.Paciente;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.MyViewHolder> implements Filterable {

    private List<Paciente> pdata;
    private List<Paciente> pdataFull;
    private View.OnClickListener listener;

    public RecyclerViewAdaptador(List<Paciente> pdata) {
        this.pdata = pdata;
        this.pdataFull = new ArrayList<>(pdata);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.paciente_fila_item, parent, false);
        if(listener!=null)vista.setOnClickListener(listener);
        return new MyViewHolder(vista);
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public Paciente getPaciente(int pos) {
        return pdata.get(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nombre_ape.setText("Nombre y Apellido: " +pdata.get(position).getNombre() + " " + pdata.get(position).getApellido());
        holder.cedula.setText("Cedula: "+pdata.get(position).getCedula());
        holder.email.setText("Email: "+ pdata.get(position).getEmail());
        holder.telefono.setText("Telefono: " + pdata.get(position).getTelefono());
        holder.img.setImageResource(R.drawable.usuario);

    }

    @Override
    public int getItemCount() {
        return pdata.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_ape;
        TextView cedula;
        TextView email;
        TextView telefono;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre_ape = itemView.findViewById(R.id.id_nom_ape);
            cedula = itemView.findViewById(R.id.id_cedula);
            email = itemView.findViewById(R.id.id_email);
            telefono = itemView.findViewById(R.id.id_telefono);
            img = itemView.findViewById(R.id.id_icono);
        }
    }

    @Override
    public Filter getFilter() {
        return pacienteFiltrar;
    }

    private Filter pacienteFiltrar = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Paciente> listaFiltro = new ArrayList<>();

            if ( constraint == null || constraint.length() == 0){
                listaFiltro.addAll(pdataFull);
            }
            else{

                String parametroFiltro = constraint.toString().toLowerCase().trim();

                for ( Paciente pItem: pdataFull){

                    if( pItem.getNombre().toLowerCase().contains(parametroFiltro)){
                            listaFiltro.add(pItem);
                    }
                }

            }

            FilterResults resultado = new FilterResults();
            resultado.values = listaFiltro;

            return resultado;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            pdata.clear();
            pdata.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
