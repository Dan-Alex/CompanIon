package com.alexdan.docflow.models;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String text;

    @OneToOne(targetEntity = User.class)
    private User toWhom;

    @OneToOne(targetEntity = User.class)
    private User fromWhom;

    @Enumerated(EnumType.STRING)
    public final TasksStatuses status;

    private enum TasksStatuses{PERFORMED, NEW, COMPLETED }

}
