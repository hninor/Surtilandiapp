package unal.edu.co.surtilandiapp.core.comm.interfaces;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import unal.edu.co.surtilandiapp.core.comm.entities.LoginRequest;
import unal.edu.co.surtilandiapp.core.comm.entities.LoginResponse;


/**
 * Created by oamezquita on 01/12/2016.
 */

public interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<LoginResponse> conexionLogin(@Body LoginRequest request);

    @POST("Registrar/SegundoFactor")
    Call<LoginResponse> segundoFactor(@Header("AccessToken") String token, @Body String code);
}
