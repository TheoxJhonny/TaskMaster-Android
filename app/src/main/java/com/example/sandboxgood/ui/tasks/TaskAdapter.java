package com.example.sandboxgood.ui.tasks;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sandboxgood.R;
import com.example.sandboxgood.model.TaskItem;

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
        holder.checkCompleted.setText(task.getTitle());
        holder.checkCompleted.setChecked(task.isCompleted());
        holder.meta.setText(task.getCategory() + " · Prioridad " + task.getPriority());
        holder.rating.setRating(task.getEffort());
        holder.checkCompleted.setPaintFlags(task.isCompleted()
                ? holder.checkCompleted.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                : holder.checkCompleted.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);

        holder.checkCompleted.setOnCheckedChangeListener((button, checked) -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;
            tasks.get(adapterPosition).setCompleted(checked);
            notifyItemChanged(adapterPosition);
            listener.onChanged();
        });
    }

    @Override public int getItemCount() { return tasks.size(); }

    static class TaskHolder extends RecyclerView.ViewHolder {
        final CheckBox checkCompleted;
        final TextView meta;
        final RatingBar rating;

        TaskHolder(View itemView) {
            super(itemView);
            checkCompleted = itemView.findViewById(R.id.checkCompleted);
            meta = itemView.findViewById(R.id.tvTaskMeta);
            rating = itemView.findViewById(R.id.taskRating);
        }
    }
}
