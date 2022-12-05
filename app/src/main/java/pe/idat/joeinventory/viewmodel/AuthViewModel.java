package pe.idat.joeinventory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import pe.idat.joeinventory.retrofit.InventoryClient;
import pe.idat.joeinventory.retrofit.request.RequestLogin;
import pe.idat.joeinventory.retrofit.response.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    public MutableLiveData<ResponseLogin> loginMutableLiveData
            = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public void autenticarUsuario(
            RequestLogin requestLogin){
        new InventoryClient().getINSTANCE().login(requestLogin)
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        loginMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
