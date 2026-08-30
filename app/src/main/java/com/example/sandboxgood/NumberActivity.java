package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NumberActivity extends AppCompatActivity {

    private EditText etNumber;
    private TextView tvResultado;

    private Button btnCalcular;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_number);

        etNumber = findViewById(R.id.etNumber);
        tvResultado = findViewById(R.id.tvResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnVolver = findViewById(R.id.btnVolver);

        btnCalcular.setOnClickListener(v -> {

            String texto = etNumber.getText().toString().trim();

            if (texto.isEmpty()) {

                etNumber.setError(
                        "Introduce un número"
                );

                return;
            }

            try {

                double numero = Double.parseDouble(texto);

                double resultado = numero * 2;

                tvResultado.setText(
                        "El doble de "
                                + numero
                                + " es "
                                + resultado
                );

            } catch (NumberFormatException e) {

                etNumber.setError(
                        "Introduce un número válido"
                );
            }
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
