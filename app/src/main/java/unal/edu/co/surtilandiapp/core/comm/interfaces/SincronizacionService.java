package unal.edu.co.surtilandiapp.core.comm.interfaces;


import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import unal.edu.co.surtilandiapp.core.comm.entities.OrdenTrabajoResponse;

/**
 * Created by oamezquita on 01/12/2016.
 */

public interface SincronizacionService {

    @POST("Obtener/OrdenesDeTrabajo")
    Call<OrdenTrabajoResponse> sincronizarOrdenes(@Header("AccessToken") String token);




}
