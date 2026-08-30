package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView tvResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rating_bar);

        ratingBar = findViewById(R.id.ratingBar);
        tvResultado = findViewById(R.id.tvResultado);
        btnVolver = findViewById(R.id.btnVolver);

        ratingBar.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {

                    tvResultado.setText(
                            "Tu valoración: "
                                    + rating
                                    + " / 5"
                    );
                }
        );

        btnVolver.setOnClickListener(v -> finish());
    }
}
