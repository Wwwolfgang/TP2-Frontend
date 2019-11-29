package com.example.tp_frontend_androidapp;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FichaClinica implements Serializable {
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

    public Integer getIdFichaClinica() {
        return idFichaClinica;
    }

    public void setIdFichaClinica(Integer idFichaClinica) {
        this.idFichaClinica = idFichaClinica;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Paciente getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Paciente idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Paciente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Paciente idCliente) {
        this.idCliente = idCliente;
    }

    public SubCategoria getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(SubCategoria idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getFechaHoraCadena() {
        return fechaHoraCadena;
    }

    public void setFechaHoraCadena(String fechaHoraCadena) {
        this.fechaHoraCadena = fechaHoraCadena;
    }

    public String getFechaDesdeCadena() {
        return fechaDesdeCadena;
    }

    public void setFechaDesdeCadena(String fechaDesdeCadena) {
        this.fechaDesdeCadena = fechaDesdeCadena;
    }

    public String getFechaHastaCadena() {
        return fechaHastaCadena;
    }

    public void setFechaHastaCadena(String fechaHastaCadena) {
        this.fechaHastaCadena = fechaHastaCadena;
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
