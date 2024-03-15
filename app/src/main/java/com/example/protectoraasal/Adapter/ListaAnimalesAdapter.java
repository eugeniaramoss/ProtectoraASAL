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
import com.example.protectoraasal.Activity.DetallesAnimalesActivity;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.R;

import java.util.ArrayList;

public class ListaAnimalesAdapter extends RecyclerView.Adapter<ListaAnimalesAdapter.viewholder> {
    ArrayList<Animales> items;
    Context context;

    public ListaAnimalesAdapter(ArrayList<Animales> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public ListaAnimalesAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_listaanimales, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAnimalesAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getNombre());
        holder.sexoTxt.setText(items.get(position).getSexo());
        holder.edadTxt.setText(items.get(position).getEdad() + " año/s");

        Glide.with(context)
                .load(items.get(position).getRutaImagen())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallesAnimalesActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt, edadTxt, rateTxt, sexoTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleTxt);
            edadTxt=itemView.findViewById(R.id.precioTxt);
            rateTxt=itemView.findViewById(R.id.rateTxt);
            sexoTxt=itemView.findViewById(R.id.sexoTxt);
            pic=itemView.findViewById(R.id.pic);


        }
    }
}
