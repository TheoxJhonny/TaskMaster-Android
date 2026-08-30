package com.example.sandboxgood;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    public interface OnTaskChanged { void onChanged(); }
    private final List<TaskItem> tasks;
    private final OnTaskChanged listener;

    public TaskAdapter(List<TaskItem> tasks, OnTaskChanged listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        TaskItem task = tasks.get(position);
        holder.checkCompleted.setOnCheckedChangeListener(null);
        holder.checkCompleted.setText(task.title);
        holder.checkCompleted.setChecked(task.completed);
        holder.meta.setText(task.category + " · Prioridad " + task.priority);
        holder.rating.setRating(task.effort);
        holder.checkCompleted.setPaintFlags(task.completed
                ? holder.checkCompleted.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                : holder.checkCompleted.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        holder.checkCompleted.setOnCheckedChangeListener((button, checked) -> {
            task.completed = checked;
            notifyItemChanged(holder.getBindingAdapterPosition());
            listener.onChanged();
        });
    }

    @Override public int getItemCount() { return tasks.size(); }

    static class TaskHolder extends RecyclerView.ViewHolder {
        CheckBox checkCompleted;
        TextView meta;
        RatingBar rating;
        TaskHolder(View itemView) {
            super(itemView);
            checkCompleted = itemView.findViewById(R.id.checkCompleted);
            meta = itemView.findViewById(R.id.tvTaskMeta);
            rating = itemView.findViewById(R.id.taskRating);
        }
    }
}
