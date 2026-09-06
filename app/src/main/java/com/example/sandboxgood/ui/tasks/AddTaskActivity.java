package com.example.sandboxgood.ui.tasks;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sandboxgood.R;
import com.example.sandboxgood.data.TaskRepository;
import com.example.sandboxgood.model.TaskItem;
import com.google.android.material.button.MaterialButton;

public class AddTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories));
        btnCancel.setOnClickListener(v -> finish());
        btnSave.setOnClickListener(v -> saveTask(etTitle, spinner, priorityGroup,
                rating, checkReminder, checkImportant));
    }

    private void saveTask(EditText titleField, Spinner category, RadioGroup priorityGroup,
                          RatingBar effort, CheckBox reminder, CheckBox important) {
        String title = titleField.getText().toString().trim();
        if (title.isEmpty()) {
            titleField.setError("Escribe una tarea");
            titleField.requestFocus();
            return;
        }

        String priority = priorityGroup.getCheckedRadioButtonId() == R.id.radioHigh ? "Alta"
                : priorityGroup.getCheckedRadioButtonId() == R.id.radioLow ? "Baja" : "Media";
        if (important.isChecked()) priority = "Alta";

        TaskItem task = new TaskItem(title, category.getSelectedItem().toString(), priority,
                Math.max(1, Math.round(effort.getRating())), false);
        new TaskRepository(this).add(task);

        String message = reminder.isChecked()
                ? "Tarea guardada con recordatorio local" : "Tarea guardada";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
