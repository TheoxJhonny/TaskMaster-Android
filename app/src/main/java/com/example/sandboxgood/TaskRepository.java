package com.example.sandboxgood;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final String PREFS = "taskmaster_tasks";
    private static final String KEY = "items";

    public static List<TaskItem> load(Context context) {
        List<TaskItem> items = new ArrayList<>();
        String raw = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, "");
        if (raw.isEmpty()) {
            items.add(new TaskItem("Revisar contenidos de Android", "Estudio", "Alta", 4, false));
            items.add(new TaskItem("Preparar materiales para mañana", "Personal", "Media", 3, true));
            save(context, items);
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

    public static void add(Context context, TaskItem task) {
        List<TaskItem> items = load(context);
        items.add(0, task);
        save(context, items);
    }

    public static void save(Context context, List<TaskItem> items) {
        JSONArray array = new JSONArray();
        try {
            for (TaskItem task : items) {
                JSONObject object = new JSONObject();
                object.put("title", task.title);
                object.put("category", task.category);
                object.put("priority", task.priority);
                object.put("effort", task.effort);
                object.put("completed", task.completed);
                array.put(object);
            }
        } catch (Exception ignored) { }
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY, array.toString()).apply();
    }
}
