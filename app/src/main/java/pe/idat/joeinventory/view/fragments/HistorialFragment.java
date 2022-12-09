package pe.idat.joeinventory.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.FragmentHistorialBinding;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import pe.idat.joeinventory.viewmodel.ProductoViewModel;


public class HistorialFragment extends Fragment implements View.OnClickListener {

    FragmentHistorialBinding binding;
    private ProductoViewModel productoViewModel;
    ResponseProducto responseProducto =new ResponseProducto();


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
                    }
                });
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Buscar();
    }

    private void Buscar(){
        productoViewModel.BuscarProducto(binding.etcodigobus.getText().toString());
        binding.tvNombre.setText(responseProducto.getNombre());
        binding.tvDescripcion.setText(responseProducto.getDetalle());
//        binding.tvCantidad.setText(responseProducto.getCantidad().toString());
    }

    private void Codigo(){
    }
}