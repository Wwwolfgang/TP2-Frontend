package com.example.tp_frontend_androidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserva {
    @SerializedName("idReserva")
    @Expose
    private Integer idReserva;
    @SerializedName("fechaCadena")
    @Expose
    private String fechaCadena;
    @SerializedName("horainicioCadena")
    @Expose
    private String horainicioCadena;
    @SerializedName("horafinCadena")
    @Expose
    private String horafinCadena;
    @SerializedName("flagAsistio")
    @Expose
    private String flagAsistio;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("posicion")
    @Expose
    private Integer posicion;
    /*@SerializedName("idEmpleado")
    @Expose
    private Integer idEmpleado;*/
    //private Integer idFichaClinica;
    //private Integer idCliente;

    public Reserva(){
    }
    public Integer getIdReserva(){return idReserva;}

    public void setIdReserva(Integer idReserva){this.idReserva = idReserva;}

    public String getFechaCadena(){return fechaCadena;}

    public void setFechaCadena(String fechaCadena){this.fechaCadena = fechaCadena;}

    public String getHorainicioCadena(){return horainicioCadena;}

    public void setHorainicioCadena(String horainicioCadena){this.horainicioCadena = horainicioCadena;}

    public String getHorafinCadena(){return horafinCadena;}

    public void setHorafinCadena(String horafinCadena){this.horafinCadena = horafinCadena;}

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

