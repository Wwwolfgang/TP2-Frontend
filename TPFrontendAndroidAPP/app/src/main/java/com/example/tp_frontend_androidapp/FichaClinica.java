package com.example.tp_frontend_androidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FichaClinica {
    @SerializedName("idFichaClinica")
    @Expose
    private Integer idFichaClinica;
    @SerializedName("fechaHora")
    @Expose
    private String fechaHora;

    @SerializedName("motivoConsulta")
    @Expose
    private String motivoConsulta;

    @SerializedName("diagnostico")
    @Expose
    private String diagnostico;

    @SerializedName("observacion")
    @Expose
    private String observacion;

    @SerializedName("idEmpleado")
    @Expose
    private Paciente idEmpleado;

    @SerializedName("idCliente")
    @Expose
    private Paciente idCliente;

    @SerializedName("idTipoProducto")
    @Expose
    private SubCategoria idTipoProducto;


    @SerializedName("fechaHoraCadena")
    @Expose
    private String fechaHoraCadena;

    @SerializedName("fechaDesdeCadena")
    @Expose
    private String fechaDesdeCadena;

    @SerializedName("fechaHastaCadena")
    @Expose
    private String fechaHastaCadena;


    public FichaClinica() {
    }


}

class FichaArchivo{
    @SerializedName("idFichaArchivo")
    @Expose
    private Integer idFichaArchivo;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("urlImagen")
    @Expose
    private String urlImagen;
}
