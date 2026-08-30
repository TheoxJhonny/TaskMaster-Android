package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckBoxActivity extends AppCompatActivity {

    private CheckBox checkJava;
    private CheckBox checkAndroid;
    private CheckBox checkFirebase;

    private TextView tvResultado;

    private Button btnConfirmar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_box);

        checkJava = findViewById(R.id.checkJava);
        checkAndroid = findViewById(R.id.checkAndroid);
        checkFirebase = findViewById(R.id.checkFirebase);

        tvResultado = findViewById(R.id.tvResultado);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);

        btnConfirmar.setOnClickListener(v -> {

            StringBuilder resultado = new StringBuilder();

            if (checkJava.isChecked()) {
                resultado.append("Java\n");
            }

            if (checkAndroid.isChecked()) {
                resultado.append("Android\n");
            }

            if (checkFirebase.isChecked()) {
                resultado.append("Firebase\n");
            }

            if (resultado.length() == 0) {

                tvResultado.setText(
                        "No seleccionaste ninguna opción."
                );

            } else {

                tvResultado.setText(
                        "Seleccionaste:\n\n" + resultado
                );
            }
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
