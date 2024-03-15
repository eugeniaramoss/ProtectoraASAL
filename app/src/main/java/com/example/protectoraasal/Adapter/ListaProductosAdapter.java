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

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.viewholder> {
    ArrayList<Productos> items;
    Context context;

    public ListaProductosAdapter(ArrayList<Productos> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public ListaProductosAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_listaproductos, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaProductosAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getNombre());
        holder.stockTxt.setText(items.get(position).getStock() + " min");
        holder.precioTxt.setText(items.get(position).getPrecio() + "€");

        Glide.with(context)
                .load(items.get(position).getRutaImagen())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt, precioTxt, rateTxt, stockTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleTxt);
            precioTxt=itemView.findViewById(R.id.precioTxt);
            rateTxt=itemView.findViewById(R.id.rateTxt);
            stockTxt=itemView.findViewById(R.id.stockTxt);
            pic=itemView.findViewById(R.id.pic);


        }
    }
}
