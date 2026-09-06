package com.example.sandboxgood.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sandboxgood.R;
import com.example.sandboxgood.data.UserSession;
import com.example.sandboxgood.ui.tasks.DashboardActivity;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tvWelcomeName = findViewById(R.id.tvWelcomeName);
        MaterialButton btnDashboard = findViewById(R.id.btnDashboard);
        tvWelcomeName.setText("Hola, " + new UserSession(this).getName());
        btnDashboard.setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });
    }
}
