package com.alexdan.companion.controllers;

import com.alexdan.companion.models.company.Company;
import com.alexdan.companion.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {

        this.companyService = companyService;
    }

    @GetMapping
    public Company getCompany() {

        return companyService.getCompany();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable long id) {

        return companyService.getCompanyById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Company putCompany(@RequestBody Company company) {

        return companyService.updateCompany(company);
    }
}
