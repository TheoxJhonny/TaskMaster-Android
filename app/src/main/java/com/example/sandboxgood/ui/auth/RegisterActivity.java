package com.example.sandboxgood.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sandboxgood.R;
import com.example.sandboxgood.data.UserSession;
import com.example.sandboxgood.ui.welcome.WelcomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());
        btnRegister.setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {
        String name = textOf(etName);
        String email = textOf(etEmail);
        String password = textOf(etPassword);

        if (name.isEmpty()) {
            showError(etName, "Introduce tu nombre");
            return;
        }
        if (email.isEmpty()) {
            showError(etEmail, "Introduce tu correo");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(etEmail, "Introduce un correo válido");
            return;
        }
        if (password.isEmpty()) {
            showError(etPassword, "Introduce una contraseña");
            return;
        }
        if (password.length() < 4) {
            showError(etPassword, "Usa al menos 4 caracteres");
            return;
        }

        new UserSession(this).register(name, email, password);
        Toast.makeText(this, "Cuenta local creada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    private String textOf(TextInputEditText field) {
        return field.getText() == null ? "" : field.getText().toString().trim();
    }

    private void showError(TextInputEditText field, String message) {
        field.setError(message);
        field.requestFocus();
    }
}
