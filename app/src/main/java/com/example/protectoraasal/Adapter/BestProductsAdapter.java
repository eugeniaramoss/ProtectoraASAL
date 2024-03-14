package com.example.protectoraasal.Adapter;

import android.content.Context;
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
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.R;

import java.util.ArrayList;

public class BestProductsAdapter extends RecyclerView.Adapter<BestProductsAdapter.viewholder> {
    ArrayList<Productos> items;
    Context context;

    public BestProductsAdapter(ArrayList<Productos> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestProductsAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_products, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestProductsAdapter.viewholder holder, int position) {
        Productos currentProducto = items.get(position);
        holder.titleTxt.setText(currentProducto.getNombre());
        holder.priceTxt.setText(String.valueOf(currentProducto.getPrecio()));
        holder.stockTxt.setText(String.valueOf(currentProducto.getStock()));

        Glide.with(context)
                .load(items.get(position).getRutaImagen())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt, stockTxt, priceTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            stockTxt = itemView.findViewById(R.id.stockTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
