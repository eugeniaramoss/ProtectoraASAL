package com.example.protectoraasal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.protectoraasal.Activity.DetallesProductosActivity;
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.R;

import java.util.ArrayList;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    private ArrayList<Productos> items;
    private Context context;

    public ProductosAdapter(ArrayList<Productos> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_productos, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, int position) {
        Productos currentProducto = items.get(position);
        holder.titleTxt.setText(currentProducto.getNombre());
        holder.precioTxt.setText(String.valueOf(currentProducto.getPrecio()) + "€");
        holder.stockTxt.setText(String.valueOf(currentProducto.getStock()));

        Glide.with(context)
                .load(currentProducto.getRutaImagen())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallesProductosActivity.class);
            intent.putExtra("object", currentProducto);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, stockTxt, precioTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            stockTxt = itemView.findViewById(R.id.sexoTxt);
            precioTxt = itemView.findViewById(R.id.precioTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
