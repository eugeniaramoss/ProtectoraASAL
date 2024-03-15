package com.example.protectoraasal.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.databinding.ActivityDetallesAnimalesBinding;

public class DetallesAnimalesActivity extends AppCompatActivity {
    ActivityDetallesAnimalesBinding binding;
    private Animales object;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesAnimalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariableAnimales();
    }

    private void setVariableAnimales() {
        binding.backBtn.setOnClickListener(v -> finish());

        if (object != null) {
            Glide.with(DetallesAnimalesActivity.this).load(object.getRutaImagen()).into(binding.pic);
            binding.edadTxt.setText(object.getEdad() + " año/s");
            binding.titleTxt.setText(object.getNombre());
            binding.descripcionTxt.setText(object.getDescripcion());
            binding.sexoTxt.setText(object.getSexo());
        }
    }

    private void getIntentExtra() {
        // Check for both types of extras
        if (getIntent().hasExtra("object")) {
            object = (Animales) getIntent().getSerializableExtra("object");
        }
        binding.backBtn.setOnClickListener(v -> finish());
    }
}
