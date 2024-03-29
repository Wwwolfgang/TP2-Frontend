package com.example.tp_frontend_androidapp;

import com.example.tp_frontend_androidapp.servicios.CategoriaService;
import com.example.tp_frontend_androidapp.servicios.DoctorService;
import com.example.tp_frontend_androidapp.servicios.FichaClinicaService;
import com.example.tp_frontend_androidapp.servicios.ReservaService;
import com.example.tp_frontend_androidapp.servicios.SubCategoriaService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicios {
    public static FichaClinicaService getFichaClinicaService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(FichaClinicaService.class);
    }

    public static CategoriaService getCategoriaService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(CategoriaService.class);
    }

    public static SubCategoriaService getSubCategoriaService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(SubCategoriaService.class);
    }

    public static DoctorService getDoctorService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(DoctorService.class);
    }
    public static ReservaService getReservaService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(ReservaService.class);
    }


    public static Retrofit getClient(String baseUrl) {

        return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }
}
