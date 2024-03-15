package com.example.protectoraasal.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectoraasal.Domain.Productos;
import com.example.protectoraasal.R;
import com.example.protectoraasal.databinding.ActivityPrivateBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PrivateActivity extends BaseActivity {
    private ActivityPrivateBinding binding;
    FirebaseAuth mAuth;
    private RecyclerView productsRecyclerView;
    private ProductCRUD productCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        // Verificar si el usuario está autenticado y es administrador
        if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")) {
            // Si es administrador, inicializar la lógica de CRUD de productos
            productCRUD = new ProductCRUD();

            Productos producto = new Productos();
            productCRUD.addProduct(producto);

            productCRUD.getAllProducts(new ProductCRUD.DataStatus() {
                @Override
                public void DataIsLoaded(ArrayList<Productos> products) {
                    // Hacer algo con los productos obtenidos
                }
            });

            String productId = "ID_DEL_PRODUCTO_A_ACTUALIZAR";
            Productos nuevoProducto = new Productos();
            productCRUD.updateProduct(productId, nuevoProducto);

            String productoId = "ID_DEL_PRODUCTO_A_ELIMINAR";
            productCRUD.deleteProduct(productId);
        } else {
            // Si no es administrador, mostrar mensaje de error y cerrar sesión
            Toast.makeText(this, "No tienes permisos de administrador", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        }
    }
}
