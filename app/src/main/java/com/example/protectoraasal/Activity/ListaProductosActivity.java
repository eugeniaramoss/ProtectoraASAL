package com.example.protectoraasal.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectoraasal.Activity.BaseActivity;
import com.example.protectoraasal.Adapter.ListaProductosAdapter;
import com.example.protectoraasal.Adapter.ProductosAdapter;
import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.databinding.ActivityListaProductosBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaProductosActivity extends BaseActivity {
    ActivityListaProductosBinding binding;
    private RecyclerView.Adapter adapterListaProductos;
    private int productoId;
    private String busquedaName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView productosListView = binding.productosListView;


        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Productos");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Productos> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Nombre").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            query = myRef.orderByChild("Producto Id").equalTo(productoId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Productos.class));
                    }
                    if (list.size() > 0) {
                        binding.productosListView.setLayoutManager(new GridLayoutManager(ListaProductosActivity.this, 2));
                        adapterListaProductos = new ListaProductosAdapter(list);
                        binding.productosListView.setAdapter(adapterListaProductos);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        productoId = getIntent().getIntExtra("Producto Id", 0);
        busquedaName = getIntent().getStringExtra("Nombre");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.titleTxt.setText(busquedaName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
}