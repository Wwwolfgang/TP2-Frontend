package com.example.tp_frontend_androidapp.servicios;

import com.example.tp_frontend_androidapp.Lista;
import com.example.tp_frontend_androidapp.Reserva;

import com.example.tp_frontend_androidapp.modelos.FichaClinica;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import retrofit2.http.POST;

import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReservaService {
    @GET("persona/{id}/agenda")
    Call<Reserva[]> obtenerHorarios(@Path("id") int id, @Query("fecha") String fecha,@Query("disponible") String disponible
    );


    @Headers("Content-Type: application/json")
    @POST("reserva")
    Call<Reserva> cargarReserva(@Body Reserva reserva, @Header("usuario") String usuario);


}
