package com.example.tp_frontend_androidapp.modelos;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FichaArchivo{
    @SerializedName("idFichaArchivo")
    @Expose
    private Integer idFichaArchivo;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("urlImagen")
    @Expose
    private String urlImagen;

    private boolean debe_agregarse;

    public boolean isDebe_agregarse() {
        return debe_agregarse;
    }

    public void setDebe_agregarse(boolean debe_agregarse) {
        this.debe_agregarse = debe_agregarse;
    }

    public Integer getIdFichaArchivo() {
        return idFichaArchivo;
    }

    public void setIdFichaArchivo(Integer idFichaArchivo) {
        this.idFichaArchivo = idFichaArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Uri uri;
}
