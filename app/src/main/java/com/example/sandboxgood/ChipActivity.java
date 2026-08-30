package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class ChipActivity extends AppCompatActivity {

    private Chip chipAndroid;
    private Chip chipJava;
    private Chip chipFirebase;

    private TextView tvResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chip);

        chipAndroid = findViewById(R.id.chipAndroid);
        chipJava = findViewById(R.id.chipJava);
        chipFirebase = findViewById(R.id.chipFirebase);

        tvResultado = findViewById(R.id.tvResultado);
        btnVolver = findViewById(R.id.btnVolver);

        CompoundButton.OnCheckedChangeListener listener =
                (buttonView, isChecked) -> actualizarResultado();

        chipAndroid.setOnCheckedChangeListener(listener);
        chipJava.setOnCheckedChangeListener(listener);
        chipFirebase.setOnCheckedChangeListener(listener);

        btnVolver.setOnClickListener(v -> finish());
    }

    private void actualizarResultado() {

        StringBuilder resultado = new StringBuilder();

        if (chipAndroid.isChecked()) {
            resultado.append("Android\n");
        }

        if (chipJava.isChecked()) {
            resultado.append("Java\n");
        }

        if (chipFirebase.isChecked()) {
            resultado.append("Firebase\n");
        }

        if (resultado.length() == 0) {

            tvResultado.setText(
                    "No hay ninguna categoría seleccionada"
            );

        } else {

            tvResultado.setText(
                    "Categorías seleccionadas:\n\n"
                            + resultado
            );
        }
    }
}
