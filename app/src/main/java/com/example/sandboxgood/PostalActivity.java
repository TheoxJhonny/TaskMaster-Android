package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PostalActivity extends AppCompatActivity {

    private EditText etPostal;
    private Button btnValidar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_postal);

        etPostal = findViewById(R.id.etPostal);
        btnValidar = findViewById(R.id.btnValidar);
        btnVolver = findViewById(R.id.btnVolver);

        btnValidar.setOnClickListener(v -> {

            String postal = etPostal.getText().toString().trim();

            if (postal.isEmpty()) {

                etPostal.setError(
                        "Introduce un código postal"
                );

                return;
            }

            if (postal.length() < 4) {

                etPostal.setError(
                        "El código postal parece demasiado corto"
                );

                return;
            }

            Toast.makeText(
                    this,
                    "Código postal: " + postal,
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
