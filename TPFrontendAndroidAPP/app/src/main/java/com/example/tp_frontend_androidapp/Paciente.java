package com.example.tp_frontend_androidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paciente {

    @SerializedName("idPersona")
    @Expose
    private Integer idPersona;

    @SerializedName("nombre")
    @Expose
    private String nombre ;

    @SerializedName("apellido")
    @Expose
    private String apellido;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("ruc")
    @Expose
    private Integer ruc;

    @SerializedName("cedula")
    @Expose
    private Integer cedula ;

    @SerializedName("tipoPersona")
    @Expose
    private String tipoPersona ;

    @SerializedName("fechaNacimiento")
    @Expose
    private String fechaNacimiento;




}
