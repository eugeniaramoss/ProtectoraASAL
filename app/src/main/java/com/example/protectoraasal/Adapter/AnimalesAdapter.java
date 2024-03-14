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
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.R;

import java.util.ArrayList;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.viewholder> {
    ArrayList<Animales> items;
    Context context;

    public AnimalesAdapter(ArrayList<Animales> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AnimalesAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_animales, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesAdapter.viewholder holder, int position) {
        Animales currentAnimal = items.get(position);
        holder.titleTxt.setText(currentAnimal.getNombre());
        holder.edadTxt.setText(String.valueOf(currentAnimal.getEdad()) + " año/s");
        holder.sexoTxt.setText(currentAnimal.getSexo());

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
        TextView titleTxt, sexoTxt, edadTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            sexoTxt = itemView.findViewById(R.id.sexoTxt);
            edadTxt = itemView.findViewById(R.id.edadTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
