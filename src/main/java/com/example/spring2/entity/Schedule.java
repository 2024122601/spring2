package com.example.spring2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String task;

    public Schedule(String title, String task) {
        this.title = title;
        this.task = task;
    }

    public void update(String title, String task) {
        this.title = title;
        this.task = task;
    }
}
