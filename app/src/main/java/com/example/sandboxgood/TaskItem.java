package com.example.sandboxgood;

public class TaskItem {
    public String title;
    public String category;
    public String priority;
    public int effort;
    public boolean completed;

    public TaskItem(String title, String category, String priority, int effort, boolean completed) {
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.effort = effort;
        this.completed = completed;
    }
}
