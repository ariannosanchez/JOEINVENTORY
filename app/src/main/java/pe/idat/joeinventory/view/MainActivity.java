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
        binding.btnregistrar.setOnClickListener(this);
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
            Toast.makeText(this, responseLogin.getMensaje(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }else{
            Toast.makeText(this, responseLogin.getMensaje(), Toast.LENGTH_SHORT).show();
            setearControles();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btniniciarlogin){
            autenticarUsuario();
        }else {
            startActivity(new Intent(MainActivity.this, RegistroActivity.class));
        }
    }

    private void autenticarUsuario() {
        if (binding.etusuariologin.getText().toString().isEmpty()){
            binding.etusuariologin.setError("Ingrese su usuario");
            binding.etusuariologin.setFocusable(true);
        }
        else if (binding.etpasswordlogin.getText().toString().isEmpty()){
            binding.etpasswordlogin.setError("Ingrese su contraseña");
            binding.etpasswordlogin.setFocusable(true);
        }else{
            RequestLogin requestLogin = new RequestLogin();
            requestLogin.setUsuario(binding.etusuariologin.getText().toString());
            requestLogin.setPassword(binding.etpasswordlogin.getText().toString());
            authViewModel.autenticarUsuario(requestLogin);
        }
    }

    private void setearControles(){
        binding.etusuariologin.setText("");
        binding.etpasswordlogin.setText("");
    }

}