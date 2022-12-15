package pe.idat.joeinventory.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.idat.joeinventory.databinding.FragmentInformacionBinding;


public class InformacionFragment extends Fragment implements View.OnClickListener {

    FragmentInformacionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInformacionBinding.inflate(inflater, container, false);
        binding.btnIrTerminos.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Intent ir_p_terminos_politicas = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/terminosypoliticasjoeinventory/p%C3%A1gina-principal"));
        startActivity(ir_p_terminos_politicas);
    }
}