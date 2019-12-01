package actividadPaciente.Servicio;

import actividadPaciente.Modelo.ListaPaciente;
import actividadPaciente.Modelo.Paciente;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPaciente {

    @GET("persona?orderBy=nombre&orderDir=asc")
        Call<ListaPaciente<Paciente>>  getDatos();


    @FormUrlEncoded
    @POST("persona")
    Call<Paciente> createPaciente(

            @Field("nombre") String nombre,
            @Field("apellido") String apellido,
            @Field("email") String email,
            @Field("telefono") int telefono,
            @Field("ruc") int ruc,
            @Field("cedula") int cedula,
            @Field("tipoPersona") String tipoPersona,
            @Field("fechaNacimiento") String fechaNacimiento
    );
}
