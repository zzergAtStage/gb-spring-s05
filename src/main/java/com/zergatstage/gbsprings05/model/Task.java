package com.zergatstage.gbsprings05.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс для определения структуры задач, используемых для ведения статуса в проекте
 */
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @NonNull
    private String description;
    private Status status;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Task(String title, @NonNull String description){
        this.title = title;
        this.description = description;
        this.status = Status.CREATED;
        this.created = LocalDateTime.now();
    }

    public Task(){
        this.status = Status.CREATED;
        this.created = LocalDateTime.now();
    }


    public enum Status{
        CREATED,
        UPDATED,
        IN_PROGRESS,
        REJECTED,
        DONE
    }

}
