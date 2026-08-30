package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwitchActivity extends AppCompatActivity {

    private Switch switchFuncion;
    private TextView tvResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_switch);

        switchFuncion = findViewById(R.id.switchFuncion);
        tvResultado = findViewById(R.id.tvResultado);
        btnVolver = findViewById(R.id.btnVolver);

        switchFuncion.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (isChecked) {

                        tvResultado.setText(
                                "La función está ACTIVADA"
                        );

                    } else {

                        tvResultado.setText(
                                "La función está DESACTIVADA"
                        );
                    }
                }
        );

        btnVolver.setOnClickListener(v -> finish());
    }
}
