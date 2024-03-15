package com.example.protectoraasal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.protectoraasal.R;
import com.example.protectoraasal.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setVariable();
    }

    private void setVariable() {
        binding.registerBtn.setOnClickListener(v -> {
            String email = binding.userEdt.getText().toString();
            String password = binding.passEdt.getText().toString();

            if (password.length() < 8) {
                Toast.makeText(RegisterActivity.this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();
                return;
            }

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, task -> {
                if (task.isSuccessful()) {
                    Log.i(TAG, "onComplete: ");
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                } else {
                    Log.i(TAG, "failure: ", task.getException());
                    Toast.makeText(RegisterActivity.this, "Autenticación fallida", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}