package com.example.sandboxgood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etRegisterEmail;
    private TextInputEditText etRegisterPassword;

    private MaterialButton btnRegister;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        // Volver al Login
        btnBack.setOnClickListener(v -> {
            finish();
        });

        // Crear cuenta
        btnRegister.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etRegisterEmail.getText().toString().trim();
            String password = etRegisterPassword.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Introduce tu nombre");
                etName.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                etRegisterEmail.setError("Introduce tu correo");
                etRegisterEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etRegisterEmail.setError("Introduce un correo válido");
                etRegisterEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etRegisterPassword.setError("Introduce una contraseña");
                etRegisterPassword.requestFocus();
                return;
            }

            if (password.length() < 4) {
                etRegisterPassword.setError("Usa al menos 4 caracteres");
                etRegisterPassword.requestFocus();
                return;
            }

            new UserSession(this).register(name, email, password);
            Toast.makeText(this, "Cuenta local creada", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, WelcomeActivity.class));
            finish();
        });
    }
}
