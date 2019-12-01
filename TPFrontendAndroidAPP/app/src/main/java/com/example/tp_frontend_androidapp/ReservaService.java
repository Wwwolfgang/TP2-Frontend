package com.example.tp_frontend_androidapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReservaService {
    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservas(@Query("orderBy") String orderBy,
                                         @Query("ejemplo") String ejemplo);

    @POST("reserva")
    Call<Reserva> agregarReserva(@Body Reserva reserva);

    @Headers("Content-Type: application/json")
    @PUT("reserva")
    Call<Reserva> modificarReserva(@Body Reserva reserva, @Header("usuario") String usuario);

    @DELETE("reserva/{id}")
    Call<Integer> cancelarReserva(@Path("id") int id);

}
