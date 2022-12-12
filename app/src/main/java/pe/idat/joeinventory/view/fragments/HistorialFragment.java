package pe.idat.joeinventory.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.FragmentHistorialBinding;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import pe.idat.joeinventory.viewmodel.ProductoViewModel;


public class HistorialFragment extends Fragment implements View.OnClickListener {

    FragmentHistorialBinding binding;
    ProductoViewModel productoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistorialBinding.inflate(inflater, container, false);

        binding.btnBuscar.setOnClickListener(this);
        productoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        productoViewModel.buscarProductoMutableLiveData.observe(requireActivity(),
                new Observer<ResponseProducto>() {
                    @Override
                    public void onChanged(ResponseProducto responseProducto) {
                        SetearValores(responseProducto);
                    }
                });
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==binding.btnBuscar.getId()) Buscar();
    }

    private void Buscar(){
//        productoViewModel.BuscarProducto(binding.etcodigobus.getText().toString().trim());
        String cod=binding.etcodigobus.getText().toString().trim();
        Log.i("COD:", cod);
        if(!cod.equals("")){
            productoViewModel.BuscarProducto(cod);
            Log.i("BP:", cod);

        }
    }


    private void SetearValores(ResponseProducto res){
        if (res.getCodigo().equals("null")){
            binding.etnombre.setText("No se encontraron resultados");
            binding.etmarca.setText("");
            binding.etcantidad.setText("");
            binding.etproveedor.setText("");
            binding.etdetalle.setText("");
            Glide.with(binding.getRoot())
                    .load("https://aq-fes.com/uk/wp-content/uploads/sites/2/2020/02/AQ_Logistics_2.gif")
                    .into(binding.ivimagen);
        }
        else{
            binding.etnombre.setText(res.getNombre());
            binding.etmarca.setText(res.getMarca());
            binding.etdetalle.setText(res.getDetalle());
            binding.etcantidad.setText( String.valueOf(res.getCantidad()));
            binding.etproveedor.setText(res.getEmailProveedor());
            Glide.with(binding.getRoot())
                    .load(res.getUrlImage())
                    .into(binding.ivimagen);
        }

    }
}