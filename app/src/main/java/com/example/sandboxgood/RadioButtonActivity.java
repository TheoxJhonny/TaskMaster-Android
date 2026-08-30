package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RadioButtonActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioSeleccionado;
    private TextView tvResultado;
    private Button btnConfirmar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_radio_button);

        radioGroup = findViewById(R.id.radioGroup);
        tvResultado = findViewById(R.id.tvResultado);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);

        btnConfirmar.setOnClickListener(v -> {

            int idSeleccionado = radioGroup.getCheckedRadioButtonId();

            if (idSeleccionado == -1) {

                tvResultado.setText(
                        "Selecciona una opción."
                );

                return;
            }

            radioSeleccionado = findViewById(idSeleccionado);

            tvResultado.setText(
                    "Has seleccionado: "
                            + radioSeleccionado.getText()
            );
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
