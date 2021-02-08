package com.alexdan.docflow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    @OneToMany(mappedBy="department", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value="department", allowSetters = true)
    List<User> employees = new ArrayList<>();

    public void addEmployee(User user){

        user.setDepartment(this);
        employees.add(user);
    }

    public void deleteEmployee(User employee){

        this.employees.remove(employee);
    }



    public Department(){}

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<User>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getCountOfEmployees() {
        return employees.size();
    }

    public User getChief() {
        return chief;
    }

    public void setChief(User chief) {
        this.chief = chief;
    }

    public List<User> getEmployees() { return employees; }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }



}
