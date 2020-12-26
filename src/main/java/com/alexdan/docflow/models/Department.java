package com.alexdan.docflow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private int countOfEmployees;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="chief_id")
    @JsonIgnoreProperties(value="department", allowSetters = true)
    private User chief;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value="department", allowSetters = true)
    List<User> employees = new ArrayList<>();



    public Department(){}

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<User>();
    }

    public void addEmployee(User user){
        user.setDepartment(this);
        employees.add(user);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountOfEmployees() {
        return employees.size();
    }

    public User getChief() {
        return chief;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChief(User chief) {
        this.chief = chief;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return name;
    }
}
