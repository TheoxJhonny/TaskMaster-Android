package com.example.sandboxgood;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    private TextView tvFecha;
    private Button btnSeleccionar;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_date);

        tvFecha = findViewById(R.id.tvFecha);
        btnSeleccionar = findViewById(R.id.btnSeleccionar);
        btnVolver = findViewById(R.id.btnVolver);

        btnSeleccionar.setOnClickListener(v -> {

            Calendar calendario = Calendar.getInstance();

            int año = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialogo = new DatePickerDialog(
                    DateActivity.this,
                    (view, year, month, dayOfMonth) -> {

                        String fecha = dayOfMonth + "/" +
                                (month + 1) + "/" +
                                year;

                        tvFecha.setText(
                                "Fecha seleccionada: " + fecha
                        );
                    },
                    año,
                    mes,
                    dia
            );

            dialogo.show();
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
