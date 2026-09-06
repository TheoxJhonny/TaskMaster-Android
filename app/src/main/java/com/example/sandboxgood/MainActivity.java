package com.example.sandboxgood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private MaterialButton btnLogin;
    private TextView tvRegister;
    private TextView tvLoginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Referencias de los elementos
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvLoginError = findViewById(R.id.tvLoginError);

        // Botón iniciar sesión
        btnLogin.setOnClickListener(v -> {

            // Ocultar el mensaje anterior antes de validar un nuevo intento.
            tvLoginError.setVisibility(View.GONE);

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Introduce tu correo");
                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Introduce tu contraseña");
                etPassword.requestFocus();
                return;
            }

            if (new UserSession(this).login(email, password)) {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            } else {
                // Mensaje único y genérico: no revela cuál credencial falló.
                tvLoginError.setText("Correo o contraseña incorrectos");
                tvLoginError.setVisibility(View.VISIBLE);
            }
        });

        // Ir al registro
        tvRegister.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }
}
