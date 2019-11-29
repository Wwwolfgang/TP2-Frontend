package com.example.tp_frontend_androidapp;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCategoria implements Serializable {

    @SerializedName("idTipoProducto")
    @Expose
    private Integer idTipoProducto;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;


    @SerializedName("idCategoria")
    @Expose
    private Categoria idCategoria;

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @NonNull
    @Override
    public String toString() {
        return descripcion;
    }
}
