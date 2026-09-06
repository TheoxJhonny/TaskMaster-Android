package com.example.sandboxgood.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sandboxgood.model.TaskItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final String PREFS = "taskmaster_tasks";
    private static final String KEY = "items";
    private final Context context;

    public TaskRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<TaskItem> load() {
        List<TaskItem> items = new ArrayList<>();
        String raw = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, "");
        if (raw.isEmpty()) {
            items.add(new TaskItem("Revisar contenidos de Android", "Estudio", "Alta", 4, false));
            items.add(new TaskItem("Preparar materiales para mañana", "Personal", "Media", 3, true));
            save(items);
            return items;
        }
        try {
            JSONArray array = new JSONArray(raw);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                items.add(new TaskItem(object.getString("title"), object.getString("category"),
                        object.getString("priority"), object.getInt("effort"),
                        object.getBoolean("completed")));
            }
        } catch (Exception ignored) { }
        return items;
    }

    public void add(TaskItem task) {
        List<TaskItem> items = load();
        items.add(0, task);
        save(items);
    }

    public void save(List<TaskItem> items) {
        JSONArray array = new JSONArray();
        try {
            for (TaskItem task : items) {
                JSONObject object = new JSONObject();
                object.put("title", task.getTitle());
                object.put("category", task.getCategory());
                object.put("priority", task.getPriority());
                object.put("effort", task.getEffort());
                object.put("completed", task.isCompleted());
                array.put(object);
            }
        } catch (Exception ignored) { }
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY, array.toString()).apply();
    }
}
