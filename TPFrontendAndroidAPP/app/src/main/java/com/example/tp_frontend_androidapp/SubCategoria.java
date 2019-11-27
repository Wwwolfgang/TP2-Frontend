package com.example.tp_frontend_androidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoria {

    @SerializedName("idTipoProducto")
    @Expose
    private Integer idTipoProducto;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("flagVisible")
    @Expose
    private String flagVisible;

    @SerializedName("posicion")
    @Expose
    private Integer posicion;

    @SerializedName("idCategoria")
    @Expose
    private Categoria idCategoria;
}
