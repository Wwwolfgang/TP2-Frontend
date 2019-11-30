package actividadPaciente.Servicio;

import actividadPaciente.Modelo.ListaPaciente;
import actividadPaciente.Modelo.Paciente;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPaciente {

    @GET("persona?orderBy=nombre&orderDir=asc")
        Call<ListaPaciente<Paciente>>  getDatos();
}
