package com.example.protectoraasal.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.databinding.ActivityDetallesProductosBinding;

public class DetallesProductosActivity extends AppCompatActivity {
    ActivityDetallesProductosBinding binding;
    private Productos object;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariableProductos();
    }

    private void setVariableProductos() {
        binding.backBtn.setOnClickListener(v -> finish());

        if (object != null) {
            // Handle Productos object
            Glide.with(DetallesProductosActivity.this).load(object.getRutaImagen()).into(binding.pic);
            binding.precioTxt.setText(object.getPrecio() + "€");
            binding.titleTxt.setText(object.getNombre());
            binding.descripcionTxt.setText(object.getDescripcion());
            binding.totalTxt.setText(num * object.getPrecio() + "€");
        }
    }

    private void getIntentExtra() {
        // Check for both types of extras
        if (getIntent().hasExtra("object")) {
            object = (Productos) getIntent().getSerializableExtra("object");
        }
        binding.backBtn.setOnClickListener(v -> finish());
    }
}
