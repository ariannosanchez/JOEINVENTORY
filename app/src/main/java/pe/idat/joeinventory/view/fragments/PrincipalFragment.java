package pe.idat.joeinventory.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.databinding.FragmentPrincipalBinding;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;
import pe.idat.joeinventory.view.adapter.ProductoAdapter;
import pe.idat.joeinventory.viewmodel.ProductoViewModel;


public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    private ProductoViewModel productoViewModel;
    private ProductoAdapter adapter = new ProductoAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPrincipalBinding.inflate(inflater, container,
                false);
        productoViewModel = new ViewModelProvider(requireActivity())
                .get(ProductoViewModel.class);
        binding.rvproductos.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvproductos.setAdapter(adapter);
        productoViewModel.listarProductos();
        productoViewModel.listProductoMutableLiveData.observe(requireActivity(),
                new Observer<List<ResponseProducto>>() {
                    @Override
                    public void onChanged(List<ResponseProducto> responseProductos) {
                        adapter.setProducto(responseProductos);
                    }
                });
        return binding.getRoot();
    }



}