package com.example.tp_frontend_androidapp.servicios;

import com.example.tp_frontend_androidapp.Lista;
import com.example.tp_frontend_androidapp.modelos.Doctor;
import com.example.tp_frontend_androidapp.modelos.SubCategoria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoctorService {

    @GET("persona")
    Call<Lista<Doctor>> obtenerDoctores(@Query("orderBy") String orderBy,
                                        @Query("orderDir") String orderDir,
                                        @Query("ejemplo") String ejemplo,
                                        @Query("like") String like
    );
}
