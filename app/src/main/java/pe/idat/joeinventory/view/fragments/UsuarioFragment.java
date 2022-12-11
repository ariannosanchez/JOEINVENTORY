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
import pe.idat.joeinventory.databinding.FragmentUsuarioBinding;
import pe.idat.joeinventory.retrofit.response.ResponseUsuario;
import pe.idat.joeinventory.view.adapter.UsuarioAdapter;
import pe.idat.joeinventory.viewmodel.UsuarioViewModel;

public class UsuarioFragment extends Fragment {

    private FragmentUsuarioBinding binding;
    private UsuarioViewModel usuarioViewModel;
    private UsuarioAdapter adapter = new UsuarioAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsuarioBinding.inflate(inflater, container, false);
        usuarioViewModel = new ViewModelProvider(requireActivity())
                .get(UsuarioViewModel.class);
        binding.rvusuarios.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvusuarios.setAdapter(adapter);
        usuarioViewModel.listarUsuarios();
        usuarioViewModel.listUsuarioMutableLiveData.observe(requireActivity(),
                new Observer<List<ResponseUsuario>>() {
                    @Override
                    public void onChanged(List<ResponseUsuario> responseUsuarios) {
                        adapter.setUsuario(responseUsuarios);
                    }
                });
        return binding.getRoot();
    }
}