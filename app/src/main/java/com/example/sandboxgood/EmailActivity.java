package com.example.sandboxgood;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnValidar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_email);

        etEmail = findViewById(R.id.etEmail);
        btnValidar = findViewById(R.id.btnValidar);
        btnVolver = findViewById(R.id.btnVolver);

        btnValidar.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Escribe un correo");
                return;
            }

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                        this,
                        "Correo válido",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                etEmail.setError("Correo no válido");
            }
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
