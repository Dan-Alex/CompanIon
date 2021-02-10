package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.CompanyFieldsRepository;
import com.alexdan.docflow.data.CompanyRepository;
import com.alexdan.docflow.models.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyFieldsRepository companyFieldsRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository, CompanyFieldsRepository companyFieldsRepository) {

        this.companyRepository = companyRepository;
        this.companyFieldsRepository = companyFieldsRepository;
    }

    @GetMapping
    public Company getCompany() {
        Company company = companyRepository.findFirstByIdIsNotNull();
           if (company != null)
                return company;
           else return new Company();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable long id) {

        return companyRepository.findById(id).get();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Company putCompany(@RequestBody Company company) {

        Company updCompany = companyRepository.save(company);
        updCompany.getFields().forEach(field -> {
                                        field.setCompany(company);
                                        field = companyFieldsRepository.save(field);
                                        });
        return updCompany;
    }
}
