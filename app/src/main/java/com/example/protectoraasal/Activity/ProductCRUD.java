package com.example.protectoraasal.Activity;

import androidx.annotation.NonNull;

import com.example.protectoraasal.Domain.Productos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductCRUD {
    private DatabaseReference databaseReference;
    private int productIdCounter = 0; // Contador de productos

    public ProductCRUD() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Productos");
        // Obtenemos el valor actual del contador de productos desde la base de datos
        databaseReference.child("productCounter").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    productIdCounter = dataSnapshot.getValue(Integer.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de lectura de la base de datos
            }
        });
    }

    public void addProduct(Productos product) {
        int productId = productIdCounter++; // Incrementamos el contador y asignamos el ID
        product.setId(productId);
        // Guardamos el nuevo valor del contador en la base de datos
        databaseReference.child("productCounter").setValue(productIdCounter);
        // Guardamos el producto en la base de datos con el ID generado
        databaseReference.child(String.valueOf(productId)).setValue(product);
    }


    public void getAllProducts(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Productos> products = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    products.add(snapshot.getValue(Productos.class));
                }
                dataStatus.DataIsLoaded(products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dataStatus.DataIsLoaded(null);
            }
        });
    }

    public void updateProduct(String productId, Productos product) {
        databaseReference.child(productId).setValue(product);
    }

    public void deleteProduct(String productId) {
        databaseReference.child(productId).removeValue();
    }

    public interface DataStatus {
        void DataIsLoaded(ArrayList<Productos> products);
    }
}
