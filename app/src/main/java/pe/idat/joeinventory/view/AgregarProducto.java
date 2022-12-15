package pe.idat.joeinventory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.ActivityAgregarProductoBinding;
import pe.idat.joeinventory.retrofit.request.RequestRegistroProducto;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import pe.idat.joeinventory.view.fragments.PrincipalFragment;
import pe.idat.joeinventory.viewmodel.AuthViewModel;

public class AgregarProducto extends AppCompatActivity implements View.OnClickListener {

    private ActivityAgregarProductoBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnregistrarproducto.setOnClickListener(this);
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        authViewModel.registroProductoMutableLiveData.observe(this,
                new Observer<ResponseProducto>() {
                    @Override
                    public void onChanged(ResponseProducto responseProducto) {
                        validarRegistroProducto(responseProducto);
                    }
                });
    }

    private void validarRegistroProducto(ResponseProducto responseProducto) {
        Toast.makeText(this, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        registrarProducto();
    }

    private void registrarProducto(){
        if (binding.etcodigopro.getText().toString().trim().isEmpty()){
            binding.etcodigopro.setError("Campo obligatorio");
            binding.etcodigopro.setFocusable(true);
        }
        else if (binding.etnombrepro.getText().toString().isEmpty()){
            binding.etnombrepro.setError("Campo obligatorio");
            binding.etnombrepro.setFocusable(true);
        }
        else if (binding.etmarcapro.getText().toString().isEmpty()){
            binding.etmarcapro.setError("Campo obligatorio");
            binding.etmarcapro.setFocusable(true);
        }
        else if (binding.etcantidadpro.getText().toString().isEmpty()){
            binding.etcantidadpro.setError("Campo obligatorio");
            binding.etcantidadpro.setFocusable(true);
        }
        else if (binding.etproveedorpro.getText().toString().isEmpty()){
            binding.etproveedorpro.setError("Campo obligatorio");
            binding.etproveedorpro.setFocusable(true);
        }
        else{
            RequestRegistroProducto requestRegistroProducto = new RequestRegistroProducto();
            requestRegistroProducto.setCodigo(binding.etcodigopro.getText().toString().trim());
            requestRegistroProducto.setNombre(binding.etnombrepro.getText().toString());
            requestRegistroProducto.setMarca(binding.etmarcapro.getText().toString());
            requestRegistroProducto.setCantidad(Integer.parseInt(binding.etcantidadpro.getText().toString()));
            requestRegistroProducto.setEmailProveedor(binding.etproveedorpro.getText().toString());
            requestRegistroProducto.setDetalle(binding.etdetallepro.getText().toString());
            authViewModel.registroProducto(requestRegistroProducto);
            setearControles();
            Toast.makeText(this, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AgregarProducto.this,
                    HomeActivity.class));
        }
    }

    private void setearControles() {
        binding.etcodigopro.setText("");
        binding.etnombrepro.setText("");
        binding.etmarcapro.setText("");
        binding.etcantidadpro.setText("");
        binding.etproveedorpro.setText("");
        binding.etdetallepro.setText("");
    }
}