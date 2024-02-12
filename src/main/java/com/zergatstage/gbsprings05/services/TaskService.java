package com.zergatstage.gbsprings05.services;

import com.zergatstage.gbsprings05.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    List<Task> getTasksByStatus(Task.Status status);
    void deleteTask(long id);

}
