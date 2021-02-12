package com.alexdan.companion.models.company;

import javax.persistence.*;

@Entity
@Table(name="company_fields")
public class CompanyFields {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String body;

    @ManyToOne(fetch=FetchType.EAGER)
    private Company company;



    public CompanyFields() {}

    public CompanyFields(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
