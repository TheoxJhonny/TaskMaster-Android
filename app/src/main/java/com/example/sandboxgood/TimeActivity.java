package com.example.sandboxgood;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity {

    private TextView tvHora;
    private Button btnSeleccionar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time);

        tvHora = findViewById(R.id.tvHora);
        btnSeleccionar = findViewById(R.id.btnSeleccionar);
        btnVolver = findViewById(R.id.btnVolver);

        btnSeleccionar.setOnClickListener(v -> {

            Calendar calendario = Calendar.getInstance();

            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);

            TimePickerDialog dialogo = new TimePickerDialog(
                    TimeActivity.this,
                    (view, hourOfDay, minute) -> {

                        String horaSeleccionada =
                                String.format(
                                        "%02d:%02d",
                                        hourOfDay,
                                        minute
                                );

                        tvHora.setText(
                                "Hora seleccionada: "
                                        + horaSeleccionada
                        );
                    },
                    hora,
                    minuto,
                    true
            );

            dialogo.show();
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
