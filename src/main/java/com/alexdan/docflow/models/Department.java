package com.alexdan.docflow.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int countOfEmployers;

    @OneToOne(targetEntity = User.class)
    private User chief;

    @OneToMany(targetEntity = User.class)
    List<User> employers = new ArrayList<>();

    public Department(){}

    public Department(String name, int countOfEmployers, User chief, List<User> employers) {
        this.name = name;
        this.countOfEmployers = countOfEmployers;
        this.chief = chief;
        this.employers = employers;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountOfEmployers() {
        return countOfEmployers;
    }

    public User getChief() {
        return chief;
    }

    public List<User> getEmployers() {
        return employers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountOfEmployers(int countOfEmployers) {
        this.countOfEmployers = countOfEmployers;
    }

    public void setChief(User chief) {
        this.chief = chief;
    }

    public void setEmployers(List<User> employers) {
        this.employers = employers;
    }
}
