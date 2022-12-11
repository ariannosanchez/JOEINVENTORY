package pe.idat.joeinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.idat.joeinventory.retrofit.InventoryClient;
import pe.idat.joeinventory.retrofit.response.ResponseUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioViewModel extends AndroidViewModel {

    public MutableLiveData<List<ResponseUsuario>> listUsuarioMutableLiveData
            = new MutableLiveData<>();

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarUsuarios(){
        new InventoryClient().getINSTANCE().listarUsuario()
                .enqueue(new Callback<List<ResponseUsuario>>() {
                    @Override
                    public void onResponse(Call<List<ResponseUsuario>> call, Response<List<ResponseUsuario>> response) {
                        listUsuarioMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ResponseUsuario>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
