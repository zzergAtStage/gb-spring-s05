package com.zergatstage.gbsprings05.repositories;

import com.zergatstage.gbsprings05.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(Task.Status status);
}
