package actividadPaciente.Servicio;

import actividadPaciente.Modelo.ListaPaciente;
import actividadPaciente.Modelo.Paciente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPaciente {

    @GET("persona?orderBy=nombre&orderDir=asc")
        Call<ListaPaciente<Paciente>>  getDatos();



    @Headers("Content-Type: application/json")
    @POST("persona")
    Call<Paciente> createPaciente(
            @Body Paciente paciente
    );
}
