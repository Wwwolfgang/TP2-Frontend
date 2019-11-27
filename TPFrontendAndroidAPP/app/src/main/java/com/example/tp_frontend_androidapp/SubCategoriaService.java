package com.example.tp_frontend_androidapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubCategoriaService {

    @GET("categoria")
    Call<Lista<Categoria>> obtenerSubCategorias(@Query("orderBy") String orderBy,
                                                @Query("orderDir") String orderDir,
                                                @Query("ejemplo") String ejemplo
    );
}
