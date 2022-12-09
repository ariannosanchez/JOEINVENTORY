package pe.idat.joeinventory.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pe.idat.joeinventory.databinding.ItemProductoBinding;
import pe.idat.joeinventory.retrofit.response.ResponseProducto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    List<ResponseProducto> listProducto = new ArrayList<>();

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,false));
    }

    public void setProducto(List<ResponseProducto> lista){
        listProducto.clear();
        listProducto.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        final ResponseProducto responseProducto = listProducto.get(position);
        holder.binding.tvcodigo.setText(responseProducto.getCodigo());
        holder.binding.tvnombre.setText(responseProducto.getNombre());
        holder.binding.tvcantidad.setText(responseProducto.getCantidad().toString());
//        Glide.with(holder.binding.ivproducto)
//                .load(responseProducto.getUrlimagen)
    }

    @Override
    public int getItemCount() {
        return listProducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding binding;
        public ViewHolder(ItemProductoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
