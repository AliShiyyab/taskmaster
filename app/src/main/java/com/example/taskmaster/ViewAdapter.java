package com.example.taskmaster;

import android.content.Intent;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;

import com.example.taskmaster.R;

import java.util.ArrayList;
import java.util.List;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.TaskViewHolder> {
    private List<Task> taskList = new ArrayList<Task>();

    public ViewAdapter(List<Task> allTasks){
        this.taskList = allTasks;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        View itemView;
        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(View->{
                Intent intent = new Intent(View.getContext(), Details.class);
                intent.putExtra("TaskName" , task.getTitle());
                intent.putExtra("TaskBody" , task.getBody());
                intent.putExtra("TaskState" , task.getState());
                View.getContext().startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public ViewAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fragment_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.TaskViewHolder holder, int position) {
        holder.task = taskList.get(position);

        TextView taskTitle = holder.itemView.findViewById(R.id.taskTitle);
        TextView taskBody = holder.itemView.findViewById(R.id.taskBody);
        TextView taskState = holder.itemView.findViewById(R.id.taskState);

        taskTitle.setText(holder.task.getTitle());
        taskBody.setText(holder.task.getBody());
        taskState.setText(holder.task.getState());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
