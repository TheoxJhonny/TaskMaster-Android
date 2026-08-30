package com.example.sandboxgood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private List<TaskItem> tasks;
    private TaskAdapter adapter;
    private TextView tvProgress, tvEmpty;
    private ProgressBar progressTasks;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvProgress = findViewById(R.id.tvProgress);
        tvEmpty = findViewById(R.id.tvEmpty);
        progressTasks = findViewById(R.id.progressTasks);
        RecyclerView recyclerTasks = findViewById(R.id.recyclerTasks);
        Button btnAddTask = findViewById(R.id.btnAddTask);
        Button btnLogout = findViewById(R.id.btnLogout);
        tvGreeting.setText("Hola, " + new UserSession(this).getName());
        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));
        tasks = TaskRepository.load(this);
        adapter = new TaskAdapter(tasks, () -> { TaskRepository.save(this, tasks); updateSummary(); });
        recyclerTasks.setAdapter(adapter);
        btnAddTask.setOnClickListener(v -> startActivity(new Intent(this, AddTaskActivity.class)));
        btnLogout.setOnClickListener(v -> {
            new UserSession(this).logout();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
        updateSummary();
    }

    @Override protected void onResume() {
        super.onResume();
        if (adapter != null) {
            tasks.clear(); tasks.addAll(TaskRepository.load(this));
            adapter.notifyDataSetChanged(); updateSummary();
        }
    }

    private void updateSummary() {
        int completed = 0;
        for (TaskItem task : tasks) if (task.completed) completed++;
        int percent = tasks.isEmpty() ? 0 : completed * 100 / tasks.size();
        progressTasks.setProgress(percent);
        tvProgress.setText(completed + " de " + tasks.size() + " tareas completadas");
        tvEmpty.setVisibility(tasks.isEmpty() ? android.view.View.VISIBLE : android.view.View.GONE);
    }
}
