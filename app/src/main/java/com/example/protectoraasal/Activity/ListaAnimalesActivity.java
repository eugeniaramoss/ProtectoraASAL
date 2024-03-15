package com.example.protectoraasal.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectoraasal.Adapter.ListaAnimalesAdapter;
import com.example.protectoraasal.Domain.Animales;
import com.example.protectoraasal.databinding.ActivityListaAnimalesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaAnimalesActivity extends BaseActivity {

    ActivityListaAnimalesBinding binding;
    private RecyclerView.Adapter adapterListaAnimales;
    private int animalId;
    private String busquedaName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaAnimalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView animalesListView = binding.animalesListView;


        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Animales");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Animales> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Nombre").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            query = myRef.orderByChild("Animal Id").equalTo(animalId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Animales.class));
                    }
                    if (list.size() > 0) {
                        binding.animalesListView.setLayoutManager(new GridLayoutManager(com.example.protectoraasal.Activity.ListaAnimalesActivity.this, 2));
                        adapterListaAnimales = new ListaAnimalesAdapter(list);
                        binding.animalesListView.setAdapter(adapterListaAnimales);
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
        animalId = getIntent().getIntExtra("Animal Id", 0);
        busquedaName = getIntent().getStringExtra("Nombre");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.titleTxt.setText(busquedaName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
};

