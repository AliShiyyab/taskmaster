package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insertAll(Task task);

    @Delete
    void deleteTask(Task task);
}
