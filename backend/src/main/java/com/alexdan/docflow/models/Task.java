package com.alexdan.docflow.models;

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
    private String report;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value={"username", "password"}, allowSetters = true)
    private User toWhom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value={"username", "password"}, allowSetters = true)
    private User fromWhom;

    @Enumerated(EnumType.STRING)
    private TasksStatuses status;

    @OneToMany(mappedBy="task", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Document> documents;

    public void addDocument(Document document){
        this.documents.add(document);
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setFiles(List<Document> documents) {
        this.documents = documents;
    }
}
