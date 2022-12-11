package pe.idat.joeinventory.retrofit;

import java.util.List;

import pe.idat.joeinventory.retrofit.request.RequestLogin;
import pe.idat.joeinventory.retrofit.request.RequestRegistro;
import pe.idat.joeinventory.retrofit.request.RequestRegistroProducto;
import pe.idat.joeinventory.retrofit.response.ResponseLogin;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import pe.idat.joeinventory.retrofit.response.ResponseRegistro;
import pe.idat.joeinventory.retrofit.response.ResponseUsuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InventoryServicio {

    @POST("login")
    public Call<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("usuario")
    public Call<ResponseRegistro> registrar(@Body RequestRegistro requestRegistro);

    @GET("producto")
    public Call<List<ResponseProducto>> listarProducto();

    @GET("usuario")
    public Call<List<ResponseUsuario>> listarUsuario();

    @GET("producto/{codigo}")
    public Call<ResponseProducto> find(@Path("codigo") String codigo);

    @POST("producto/")
    public Call<ResponseProducto> registrarProducto(@Body RequestRegistroProducto requestRegistroProducto);
}
