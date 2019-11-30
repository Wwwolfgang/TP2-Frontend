package com.example.tp_frontend_androidapp.modelos;

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

