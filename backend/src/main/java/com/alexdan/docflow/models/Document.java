package com.alexdan.docflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="documents")
public class Document {

    @Id
    private String filename;
    private String shortName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Task task;

    public Document() {}

    public Document(String filename, String shortName) {
        this.filename = filename;
        this.shortName = shortName;
    }

    public Document(String filename, String shortName, User user) {
        this.filename = filename;
        this.user = user;
        this.shortName = shortName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
