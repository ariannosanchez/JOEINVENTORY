package pe.idat.joeinventory.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.idat.joeinventory.retrofit.InventoryClient;
import pe.idat.joeinventory.retrofit.request.RequestLogin;
import pe.idat.joeinventory.retrofit.response.ResponseLogin;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class ProductoViewModel extends AndroidViewModel {

    public MutableLiveData<List<ResponseProducto>> listProductoMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<ResponseProducto> buscarProductoMutableLiveData
            = new MutableLiveData<>();

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarProductos(){
        new InventoryClient().getINSTANCE().listarProducto()
                .enqueue(new Callback<List<ResponseProducto>>() {
                    @Override
                    public void onResponse(Call<List<ResponseProducto>> call, Response<List<ResponseProducto>> response) {
                        listProductoMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ResponseProducto>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }

    public void BuscarProducto(String codigo){
        new InventoryClient().getINSTANCE().find(codigo)
                .enqueue(new Callback<ResponseProducto>() {
                    @Override
                    public void onResponse(Call<ResponseProducto> call, Response<ResponseProducto> response) {
                        Log.i("INFO", "Se cumplio con el response");
                        buscarProductoMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseProducto> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
