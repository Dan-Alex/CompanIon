package com.alexdan.docflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String text;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "toWhomUser_id")
    @JsonIgnoreProperties(value={"username", "password"}, allowSetters = true)
    private User toWhom;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "fromWhomUser_id")
    @JsonIgnoreProperties(value={"username", "password"}, allowSetters = true)
    private User fromWhom;

    @Enumerated(EnumType.STRING)
    private TasksStatuses status;

    @OneToMany(mappedBy="task", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    List<Document> files;

    public List<Document> getFiles() {
        return files;
    }

    public void setFiles(List<Document> files) {
        this.files = files;
    }

    public Task(){}

    public Task(String name, String text, User toWhom, User fromWhom, TasksStatuses status) {
        this.name = name;
        this.text = text;
        this.toWhom = toWhom;
        this.fromWhom = fromWhom;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getToWhom() {
        return toWhom;
    }

    public void setToWhom(User toWhom) {
        this.toWhom = toWhom;
    }

    public User getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(User fromWhom) {
        this.fromWhom = fromWhom;
    }

    public TasksStatuses getStatus() {
        return status;
    }

    public void setStatus(TasksStatuses status) {
        this.status = status;
    }
}
