package com.example.sandboxgood.model;

public class TaskItem {
    private final String title;
    private final String category;
    private final String priority;
    private final int effort;
    private boolean completed;

    public TaskItem(String title, String category, String priority, int effort, boolean completed) {
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.effort = effort;
        this.completed = completed;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getPriority() { return priority; }
    public int getEffort() { return effort; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
