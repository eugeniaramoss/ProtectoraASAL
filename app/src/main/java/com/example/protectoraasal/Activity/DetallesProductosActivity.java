package com.example.protectoraasal.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.protectoraasal.Domain.Usuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.databinding.ActivityDetallesProductosBinding;
import com.google.firebase.database.ValueEventListener;

public class DetallesProductosActivity extends AppCompatActivity {
    ActivityDetallesProductosBinding binding;
    private Productos object;
    private int num = 1;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Productos");

        getIntentExtra();
        setVariableProductos();

        actualizarProducto(1, "Nuevo nombre", "Nueva descripción", 100, "nueva_ruta_imagen.jpg", 10);
        eliminarProducto(1);
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

    private void actualizarProducto(int id, String nuevoNombre, String nuevaDescripcion, int nuevoPrecio, String nuevaRutaImagen, int nuevoStock) {
        Usuarios user = null;
        databaseReference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Productos producto = dataSnapshot.getValue(Productos.class);
                    if (producto != null && user.getRol_id() == 2) {
                        producto.setNombre(nuevoNombre);
                        producto.setDescripcion(nuevaDescripcion);
                        producto.setPrecio(nuevoPrecio);
                        producto.setRutaImagen(nuevaRutaImagen);
                        producto.setStock(nuevoStock);

                        databaseReference.child(String.valueOf(id)).setValue(producto);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void eliminarProducto(int id) {
        Usuarios user = null;
        databaseReference.child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Productos producto = dataSnapshot.getValue(Productos.class);
                    if (producto != null && user.getRol_id() == 2) {
                        dataSnapshot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
