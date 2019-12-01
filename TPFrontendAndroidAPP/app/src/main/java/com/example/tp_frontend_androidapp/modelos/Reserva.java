package com.example.tp_frontend_androidapp.modelos;

import androidx.annotation.NonNull;

import com.example.tp_frontend_androidapp.modelos.Paciente;
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
    @SerializedName("horaInicioCadena")
    @Expose
    private String horaInicioCadena;
    @SerializedName("horaFinCadena")
    @Expose
    private String horaFinCadena;
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
    @SerializedName("fechaDesdeCadena")
    @Expose
    private String fechaDesdeCadena;
    @SerializedName("fechaHastaCadena")
    @Expose
    private String fechaHastaCadena;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    /*@SerializedName("idEmpleado")
    @Expose
    private Integer idEmpleado;*/
    //private Integer idFichaClinica;
    //private Integer idCliente;

    @NonNull
    @Override
    public String toString() {

        return this.horaInicioCadena +"-"+this.horaFinCadena;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Reserva(){
    }
    public Integer getIdReserva(){return idReserva;}

    public void setIdReserva(Integer idReserva){this.idReserva = idReserva;}

    public String getFechaCadena(){return fechaCadena;}

    public void setFechaCadena(String fechaCadena){this.fechaCadena = fechaCadena;}

    public String getHoraInicioCadena(){return horaInicioCadena;}

    public void setHoraInicioCadena(String horaInicioCadena){this.horaInicioCadena = horaInicioCadena;}

    public String getHoraFinCadena(){return horaFinCadena;}

    public void setHoraFinCadena(String horaFinCadena){this.horaFinCadena = horaFinCadena;}

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
}

