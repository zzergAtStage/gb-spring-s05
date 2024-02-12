package com.zergatstage.gbsprings05.controller;

import com.zergatstage.gbsprings05.model.Task;
import com.zergatstage.gbsprings05.model.TasksNoDataFoundException;
import com.zergatstage.gbsprings05.services.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping()
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
        } catch (TasksNoDataFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return (taskService.createTask(task).getDescription() != null) ?
                new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable Task.Status status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable("id") Long id, @PathVariable("status") Task.Status status) {
        return new ResponseEntity<>(taskService.updateTaskStatus(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        Task taskToDeletion = taskService.getTaskById(id);
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }


}
