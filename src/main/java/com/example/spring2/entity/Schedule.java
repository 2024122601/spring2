package com.example.spring2.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user id", nullable = false)
    private User user;

    public Schedule(String title, String task, User user) {
        this.title = title;
        this.task = task;
        this.user = user;
    }

    public void update(String title, String task) {
        this.title = title;
        this.task = task;
    }
}
