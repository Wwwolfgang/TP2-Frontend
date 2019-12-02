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

import actividadPaciente.DetallePaciente.Detalle;
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

        holder.nombre_ape.setText(pdata.get(position).getNombre() + " " + pdata.get(position).getApellido());
    }

    @Override
    public int getItemCount() {
        return pdata.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_ape;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre_ape = itemView.findViewById(R.id.nombre_y_apellido);
        }
    }

    @Override
    public Filter getFilter() {
        return pacienteFiltrar;
    }

    private Filter pacienteFiltrar = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String parametroFiltro = constraint.toString().toLowerCase().trim();
            List<Paciente> listaFiltro = new ArrayList<>();

            if ( parametroFiltro.isEmpty()){
                listaFiltro.addAll(pdataFull);
            }
            else{

                for ( Paciente pItem: pdataFull){

                    if( pItem.getNombre().toLowerCase().contains(parametroFiltro) ){
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

            pdata = (List<Paciente>)results.values;

            notifyDataSetChanged();
        }
    };

}
