package com.example.tp_frontend_androidapp.servicios;

import com.example.tp_frontend_androidapp.Lista;
import com.example.tp_frontend_androidapp.modelos.Categoria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoriaService {
    @GET("categoria")
    Call<Lista<Categoria>> obtenerCategorias(@Query("orderBy") String orderBy,
                                             @Query("orderDir") String orderDir
    );
}
