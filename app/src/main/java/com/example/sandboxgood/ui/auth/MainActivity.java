package com.example.sandboxgood.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.sandboxgood.R;
import com.example.sandboxgood.data.UserSession;
import com.example.sandboxgood.ui.welcome.WelcomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextView tvLoginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);
        tvLoginError = findViewById(R.id.tvLoginError);

        btnLogin.setOnClickListener(v -> validateLogin());
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void validateLogin() {
        tvLoginError.setVisibility(View.GONE);
        String email = textOf(etEmail);
        String password = textOf(etPassword);

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
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        } else {
            tvLoginError.setVisibility(View.VISIBLE);
        }
    }

    private String textOf(TextInputEditText field) {
        return field.getText() == null ? "" : field.getText().toString().trim();
    }
}
