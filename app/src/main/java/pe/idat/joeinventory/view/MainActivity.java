package pe.idat.joeinventory.view;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.ActivityMainBinding;
import pe.idat.joeinventory.retrofit.request.RequestLogin;
import pe.idat.joeinventory.retrofit.response.ResponseLogin;
import pe.idat.joeinventory.viewmodel.AuthViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btniniciarlogin.setOnClickListener(this);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.loginMutableLiveData.observe(this, new Observer<ResponseLogin>() {
            @Override
            public void onChanged(ResponseLogin responseLogin) {
                validarAutenticacion(responseLogin);
            }
        });
    }

    private void validarAutenticacion(ResponseLogin responseLogin) {
        if (responseLogin.getRpta()){
            startActivity(new Intent(MainActivity.this, MenuActivity.class));
        }else{
            Toast.makeText(this, responseLogin.getMensaje(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        autenticarUsuario();
    }

    private void autenticarUsuario() {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setUsuario(binding.etusuariologin.getText().toString());
        requestLogin.setPassword(binding.etpasswordlogin.getText().toString());
        authViewModel.autenticarUsuario(requestLogin);
    }
}