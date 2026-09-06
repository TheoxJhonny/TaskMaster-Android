package com.example.sandboxgood.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sandboxgood.R;
import com.example.sandboxgood.data.TaskRepository;
import com.example.sandboxgood.data.UserSession;
import com.example.sandboxgood.model.TaskItem;
import com.example.sandboxgood.ui.auth.MainActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private List<TaskItem> tasks;
    private TaskAdapter adapter;
    private TaskRepository repository;
    private TextView tvProgress;
    private TextView tvEmpty;
    private ProgressBar progressTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        repository = new TaskRepository(this);
        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvProgress = findViewById(R.id.tvProgress);
        tvEmpty = findViewById(R.id.tvEmpty);
        progressTasks = findViewById(R.id.progressTasks);
        RecyclerView recyclerTasks = findViewById(R.id.recyclerTasks);
        Button btnAddTask = findViewById(R.id.btnAddTask);
        Button btnLogout = findViewById(R.id.btnLogout);

        tvGreeting.setText("Hola, " + new UserSession(this).getName());
        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));
        tasks = repository.load();
        adapter = new TaskAdapter(tasks, () -> {
            repository.save(tasks);
            updateSummary();
        });
        recyclerTasks.setAdapter(adapter);

        btnAddTask.setOnClickListener(v -> startActivity(new Intent(this, AddTaskActivity.class)));
        btnLogout.setOnClickListener(v -> logout());
        updateSummary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            tasks.clear();
            tasks.addAll(repository.load());
            adapter.notifyDataSetChanged();
            updateSummary();
        }
    }

    private void logout() {
        new UserSession(this).logout();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void updateSummary() {
        int completed = 0;
        for (TaskItem task : tasks) if (task.isCompleted()) completed++;
        int percent = tasks.isEmpty() ? 0 : completed * 100 / tasks.size();
        progressTasks.setProgress(percent);
        tvProgress.setText(completed + " de " + tasks.size() + " tareas completadas");
        tvEmpty.setVisibility(tasks.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
