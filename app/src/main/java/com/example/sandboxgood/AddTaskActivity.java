package com.example.sandboxgood;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class AddTaskActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        EditText etTitle = findViewById(R.id.etTaskTitle);
        Spinner spinner = findViewById(R.id.spinnerCategory);
        RadioGroup priorityGroup = findViewById(R.id.priorityGroup);
        RatingBar rating = findViewById(R.id.ratingEffort);
        CheckBox checkReminder = findViewById(R.id.checkReminder);
        CheckBox checkImportant = findViewById(R.id.checkImportant);
        MaterialButton btnSave = findViewById(R.id.btnSaveTask);
        MaterialButton btnCancel = findViewById(R.id.btnCancelTask);

        String[] categories = {"Estudio", "Trabajo", "Personal", "Compras"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories));
        btnCancel.setOnClickListener(v -> finish());
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            if (title.isEmpty()) { etTitle.setError("Escribe una tarea"); etTitle.requestFocus(); return; }
            String priority = priorityGroup.getCheckedRadioButtonId() == R.id.radioHigh ? "Alta"
                    : priorityGroup.getCheckedRadioButtonId() == R.id.radioLow ? "Baja" : "Media";
            if (checkImportant.isChecked()) priority = "Alta";
            TaskRepository.add(this, new TaskItem(title, spinner.getSelectedItem().toString(),
                    priority, Math.max(1, Math.round(rating.getRating())), false));
            String message = checkReminder.isChecked() ? "Tarea guardada con recordatorio local" : "Tarea guardada";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
