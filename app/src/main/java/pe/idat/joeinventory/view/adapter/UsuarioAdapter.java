package pe.idat.joeinventory.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.idat.joeinventory.databinding.ItemUsuarioBinding;
import pe.idat.joeinventory.retrofit.response.ResponseUsuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {

    List<ResponseUsuario> listUsuario = new ArrayList<>();

    @NonNull
    @Override
    public UsuarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemUsuarioBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    public void setUsuario(List<ResponseUsuario> lista){
        listUsuario.clear();
        listUsuario.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.ViewHolder holder, int position) {

        final ResponseUsuario responseUsuario = listUsuario.get(position);
        holder.binding.tvnombre.setText(responseUsuario.getNombre());
        holder.binding.tvapellidos.setText(responseUsuario.getApellido());
        holder.binding.tvdni.setText(responseUsuario.getDni());
    }

    @Override
    public int getItemCount() {
        return listUsuario.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemUsuarioBinding binding;
        public ViewHolder(ItemUsuarioBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
