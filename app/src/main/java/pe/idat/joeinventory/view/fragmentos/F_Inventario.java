package pe.idat.joeinventory.view.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pe.idat.joeinventory.R;
import pe.idat.joeinventory.view.AgregarProducto;


public class F_Inventario extends Fragment {

    FloatingActionButton FAB_AgregarProducto;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f__inventario, container, false);

        FAB_AgregarProducto = view.findViewById(R.id.FAB_AgregarProducto);
        FAB_AgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AgregarProducto.class));
            }
        });

        return view;
    }
}