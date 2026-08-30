package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneActivity extends AppCompatActivity {

    private EditText etPhone;
    private Button btnMostrar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone);

        etPhone = findViewById(R.id.etPhone);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnVolver = findViewById(R.id.btnVolver);

        btnMostrar.setOnClickListener(v -> {

            String phone = etPhone.getText().toString().trim();

            if (phone.isEmpty()) {
                etPhone.setError("Introduce un número");
                return;
            }

            Toast.makeText(
                    this,
                    "Teléfono: " + phone,
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
