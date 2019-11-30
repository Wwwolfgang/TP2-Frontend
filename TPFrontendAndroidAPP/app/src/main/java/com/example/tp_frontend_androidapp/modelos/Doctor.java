package com.example.tp_frontend_androidapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor extends Paciente{
    @SerializedName("soloUsuariosDelSistema")
    @Expose
    private Boolean soloUsuariosDelSistema;

    @SerializedName("usuarioLogin")
    @Expose
    private String usuarioLogin;

    public Boolean getSoloUsuariosDelSistema() {
        return soloUsuariosDelSistema;
    }

    public void setSoloUsuariosDelSistema(Boolean soloUsuariosDelSistema) {
        this.soloUsuariosDelSistema = soloUsuariosDelSistema;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
}
