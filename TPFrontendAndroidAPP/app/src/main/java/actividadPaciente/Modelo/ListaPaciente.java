package actividadPaciente.Modelo;

import com.google.gson.annotations.SerializedName;

public class ListaPaciente<T> {

    @SerializedName("lista")
    private T[] lista;

    @SerializedName("totalDatos")
    private int totalDatos;


    public ListaPaciente(T[] lista, int totalDatos) {
        this.lista = lista;
        this.totalDatos = totalDatos;
    }

    public T[] getLista() {
        return lista;
    }

    public void setLista(T[] lista) {
        this.lista = lista;
    }

    public int getTotalDatos() {
        return totalDatos;
    }

    public void setTotalDatos(int totalDatos) {
        this.totalDatos = totalDatos;
    }
}
