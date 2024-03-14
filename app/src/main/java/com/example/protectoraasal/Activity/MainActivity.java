package com.example.protectoraasal.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectoraasal.Adapter.AnimalesAdapter;
import com.example.protectoraasal.Adapter.ProductosAdapter;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.Domain.Sexo;
import com.example.protectoraasal.Domain.Tamanio;
import com.example.protectoraasal.Domain.Tipo;
import com.example.protectoraasal.R;
import com.example.protectoraasal.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initTipo();
        initSexo();
        initTamanio();
        initProductos();
        initAnimales();
    }

    private void initAnimales() {
        DatabaseReference myRef = database.getReference("Animales");
        binding.progressBarAnimales.setVisibility(View.VISIBLE);
        ArrayList<Animales> list = new ArrayList<>();
        Query query = myRef.orderByChild("pagPpal").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Animales.class));
                    }
                    if (list.size() > 0) {
                        binding.animalesView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new AnimalesAdapter(list);
                        binding.animalesView.setAdapter(adapter);
                    }
                    binding.progressBarAnimales.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initProductos() {
        DatabaseReference myRef = database.getReference("Productos");
        binding.progressBarProductos.setVisibility(View.VISIBLE);
        ArrayList<Productos> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestProduct").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Productos.class));
                    }
                    if (list.size() > 0) {
                        binding.bestProductView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new ProductosAdapter(list);
                        binding.bestProductView.setAdapter(adapter);
                    }
                    binding.progressBarProductos.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTipo() { // en xml activity_main seria localizacion
        DatabaseReference myRef = database.getReference("Tipo");
        ArrayList<Tipo> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Tipo.class));
                    }
                    ArrayAdapter<Tipo> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initSexo() { // en xml activity_main seria tiempo
        DatabaseReference myRef = database.getReference("Sexo");
        ArrayList<Sexo> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Sexo.class));
                    }
                    ArrayAdapter<Sexo> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTamanio() { // en xml activity_main seria precio
        DatabaseReference myRef = database.getReference("Tamanio");
        ArrayList<Tamanio> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Tamanio.class));
                    }
                    ArrayAdapter<Tamanio> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}