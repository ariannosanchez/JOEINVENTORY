package pe.idat.joeinventory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.ActivityRegistroBinding;
import pe.idat.joeinventory.retrofit.request.RequestRegistro;
import pe.idat.joeinventory.retrofit.response.ResponseRegistro;
import pe.idat.joeinventory.viewmodel.AuthViewModel;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegistroBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnloginreg.setOnClickListener(this);
        binding.btnregistarusuario.setOnClickListener(this);
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        authViewModel.registroMutableLiveData.observe(this,
                new Observer<ResponseRegistro>() {
                    @Override
                    public void onChanged(ResponseRegistro responseRegistro) {
                        validarRegistroUsuario(responseRegistro);
                    }
                });
    }

    private void validarRegistroUsuario(ResponseRegistro responseRegistro) {
        Toast.makeText(getApplicationContext(),
                responseRegistro.getMensaje(),
                Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnloginreg){
            startActivity(new Intent(RegistroActivity.this,
                    MainActivity.class));
        }else{
            registrarUsuario();
        }
    }

    private void registrarUsuario() {
        if (binding.etnombrereg.getText().toString().isEmpty()){
            binding.etnombrereg.setError("Campo obligatorio");
            binding.etnombrereg.setFocusable(true);
        }
        else if (binding.etapellidoreg.getText().toString().isEmpty()){
            binding.etapellidoreg.setError("Campo obligatorio");
            binding.etapellidoreg.setFocusable(true);
        }
        else if (binding.etusuarioreg.getText().toString().isEmpty()){
            binding.etusuarioreg.setError("Campo obligatorio");
            binding.etusuarioreg.setFocusable(true);
        }
        else if (binding.etpasswordreg.getText().toString().isEmpty()){
            binding.etpasswordreg.setError("Campo obligatorio");
            binding.etpasswordreg.setFocusable(true);
        }
        else if (binding.etdnireg.getText().toString().isEmpty()){
            binding.etdnireg.setError("Campo obligatorio");
            binding.etdnireg.setFocusable(true);
        } else{
            RequestRegistro requestRegistro = new RequestRegistro();
            requestRegistro.setNombre(binding.etnombrereg.getText().toString());
            requestRegistro.setApellido(binding.etapellidoreg.getText().toString());
            requestRegistro.setDni(binding.etdnireg.getText().toString());
            requestRegistro.setUsuario(binding.etusuarioreg.getText().toString());
            requestRegistro.setPassword(binding.etpasswordreg.getText().toString());
            authViewModel.registroUsuario(requestRegistro);
            setearControles();
        }
    }

    private void setearControles(){
        binding.etnombrereg.setText("");
        binding.etapellidoreg.setText("");
        binding.etdnireg.setText("");
        binding.etusuarioreg.setText("");
        binding.etpasswordreg.setText("");
    }
}