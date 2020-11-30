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
    @JoinColumn(name="chief_id")
    private User chief;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<User> employers = new ArrayList<>();



    public Department(){}

    public Department(String name) {
        this.name = name;
        employers = new ArrayList<User>();
    }

    public void addEmployer(User user){
        user.setDepartment(this);
        employers.add(user);
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountOfEmployers() {
        return employers.size();
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

    public void setChief(User chief) {
        this.chief = chief;
    }

    public void setEmployers(List<User> employers) {
        this.employers = employers;
    }
}
