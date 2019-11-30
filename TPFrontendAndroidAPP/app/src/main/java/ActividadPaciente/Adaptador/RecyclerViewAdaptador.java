package ActividadPaciente.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_frontend_androidapp.R;

import java.util.List;

import ActividadPaciente.Modelo.Paciente;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.MyViewHolder> {

    private Context contexto;
    private List<Paciente> pdata;
    private View.OnClickListener listener;

    public RecyclerViewAdaptador(Context contexto, List<Paciente> pdata) {
        this.contexto = contexto;
        this.pdata = pdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista;
        LayoutInflater inflater = LayoutInflater.from(contexto);
        vista = inflater.inflate(R.layout.paciente_fila_item, parent, false);
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
}
