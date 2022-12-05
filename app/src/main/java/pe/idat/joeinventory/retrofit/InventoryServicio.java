package pe.idat.joeinventory.retrofit;

import pe.idat.joeinventory.retrofit.request.RequestLogin;
import pe.idat.joeinventory.retrofit.response.ResponseLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InventoryServicio {

    @POST("login")
    public Call<ResponseLogin> login(@Body RequestLogin requestLogin);

}
