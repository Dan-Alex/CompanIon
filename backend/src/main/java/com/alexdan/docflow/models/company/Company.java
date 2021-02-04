package com.alexdan.docflow.models.company;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value="company", allowSetters = true)
    List<CompanyFields> fields;

    public Company(){
        this.fields = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompanyFields> getFields() {
        return fields;
    }

    public void setFields(List<CompanyFields> fields) {
        this.fields = fields;
    }

    public void addField(CompanyFields field){
        this.fields.add(field);
        field.setCompany(this);
    }

    public void removeField (CompanyFields field){
        this.fields.remove(field);
        field.setCompany(null);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fields=" + fields.toString() +
                '}';
    }
}
