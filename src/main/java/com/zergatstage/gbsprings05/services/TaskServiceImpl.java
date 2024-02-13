package com.zergatstage.gbsprings05.services;

import com.zergatstage.gbsprings05.model.Task;
import com.zergatstage.gbsprings05.model.NoDataFoundException;
import com.zergatstage.gbsprings05.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements business logic for application
 */
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    /**
     * Returns all tasks from database
     * @return List of tasks
     */
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Searches and returns the task defined by id
     * @param id Long id param
     * @return Existed task or NO_DATA_FOUND (hi oracle!)
     */
    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("NoData found")
        );
    }

    /**
     * Creates a new task using the incoming blueprint
     * @param task Incoming task
     * @return created in database entity
     */
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Updates existed Task in database
     * @param id Task ID
     * @param task Task blueprint
     * @return Updated task entity
     */
    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        //if status is not send set the UPDATED status
        Task.Status status = task.getStatus();
        existingTask.setStatus((status == null || status == Task.Status.CREATED) ?
                    Task.Status.UPDATED : status);
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }



    @Override
    public void deleteTask(long id) {
        //delegates throwing an exception to get method
        Task existedTask = getTaskById(id);
        taskRepository.delete(existedTask);
    }


    public List<Task> getTasksByStatus(Task.Status status) {
        return taskRepository.findAllByStatus(status);
    }

    public Task updateTaskStatus(Long id, Task.Status status) {
        Task existingTask = getTaskById(id);
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setStatus(status);
        return taskRepository.save(existingTask);
    }
}
