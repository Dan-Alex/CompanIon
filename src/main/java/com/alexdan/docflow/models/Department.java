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
    private int countOfEmployees;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="chief_id")
    private User chief;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<User> employees = new ArrayList<>();



    public Department(){}

    public Department(String name) {
        this.name = name;
        employers = new ArrayList<User>();
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
}
