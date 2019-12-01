package com.example.tp_frontend_androidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reserva implements Serializable {
    @SerializedName("idReserva")
    @Expose
    private Integer idReserva;
    @SerializedName("fechaCadena")
    @Expose
    private String fechaCadena;
    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;
    @SerializedName("horaFin")
    @Expose
    private String horaFin;
    @SerializedName("flagAsistio")
    @Expose
    private String flagAsistio;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("posicion")
    @Expose
    private Integer posicion;
    @SerializedName("idEmpleado")
    @Expose
    private Paciente idEmpleado;

    @SerializedName("idCliente")
    @Expose
    private Paciente idCliente;
    @SerializedName("fechaHoraCadena")
    @Expose
    private String fechaHoraCadena;

    @SerializedName("fechaDesdeCadena")
    @Expose
    private String fechaDesdeCadena;

    @SerializedName("fechaHastaCadena")
    @Expose
    private String fechaHastaCadena;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    public Reserva(){
    }
    public String getFechaHora() {
        return fecha;
    }

    public void setFechaHora(String fechaHora) {
        this.fecha = fechaHora;
    }

    public Integer getIdReserva(){return idReserva;}

    public void setIdReserva(Integer idReserva){this.idReserva = idReserva;}

    public String getFechaCadena(){return fechaCadena;}

    public void setFechaCadena(String fechaCadena){this.fechaCadena = fechaCadena;}

    public String getHorainicioCadena(){return horaInicio;}

    public void setHorainicioCadena(String horainicioCadena){this.horaInicio = horainicioCadena;}

    public String getHorafinCadena(){return horaFin;}

    public void setHorafinCadena(String horafinCadena){this.horaFin = horafinCadena;}

    public String getFlagAsistio(){return flagAsistio;}

    public void setFlagAsistio(String flagAsistio){ this.flagAsistio = flagAsistio;}

    public String getObservacion(){return observacion;}

    public void setObservacion(String observacion){this.observacion = observacion;}

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
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
}

