package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageButtonActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView tvResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_button);

        imageButton = findViewById(R.id.imageButton);
        tvResultado = findViewById(R.id.tvResultado);
        btnVolver = findViewById(R.id.btnVolver);

        imageButton.setOnClickListener(v -> {

            tvResultado.setText(
                    "¡Has pulsado el ImageButton!"
            );
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
