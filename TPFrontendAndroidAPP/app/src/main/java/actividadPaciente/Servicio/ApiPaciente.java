package actividadPaciente.Servicio;

import actividadPaciente.Modelo.ListaPaciente;
import actividadPaciente.Modelo.Paciente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPaciente {

    @GET("persona?orderBy=nombre&orderDir=asc")
        Call<ListaPaciente<Paciente>>  getDatos();



    @Headers("Content-Type: application/json")
    @POST("persona")
    Call<Paciente> createPaciente(
            @Body Paciente paciente
    );

    @Headers("Content-Type: application/json")
    @PUT("persona")
    Call<Void> modificarPaciente(
            @Body Paciente paciente
    );

    @DELETE("persona/{id}")
    Call<Void> eliminarPaciente(@Path("id") int id);
}
