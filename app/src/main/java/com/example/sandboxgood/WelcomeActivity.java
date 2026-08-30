package com.example.sandboxgood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    private MaterialButton btnDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        TextView tvWelcomeName = findViewById(R.id.tvWelcomeName);
        tvWelcomeName.setText("Hola, " + new UserSession(this).getName());

        btnDashboard = findViewById(R.id.btnDashboard);

        btnDashboard.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WelcomeActivity.this,
                    DashboardActivity.class
            );

            startActivity(intent);

            finish();
        });
    }
}
