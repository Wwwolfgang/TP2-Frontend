package com.example.tp_frontend_androidapp.modelos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Categoria implements Serializable {
    @SerializedName("idCategoria")
    @Expose
    private Integer idCategoria;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;


    public Categoria() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @NonNull
    @Override
    public String toString() {
        return descripcion;
    }
}
