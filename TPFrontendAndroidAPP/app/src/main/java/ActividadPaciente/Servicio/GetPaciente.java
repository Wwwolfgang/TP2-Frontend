package ActividadPaciente.Servicio;

import ActividadPaciente.Modelo.ListaPaciente;
import ActividadPaciente.Modelo.Paciente;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPaciente {

    @GET("persona")
        Call<ListaPaciente<Paciente>>  getDatos();
}
