package com.alexdan.companion.services;

import com.alexdan.companion.data.CompanyFieldsRepository;
import com.alexdan.companion.data.CompanyRepository;
import com.alexdan.companion.models.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyFieldsRepository companyFieldsRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyFieldsRepository companyFieldsRepository) {

        this.companyRepository = companyRepository;
        this.companyFieldsRepository = companyFieldsRepository;
    }

    public Company getCompany(){

        Company company = companyRepository.findFirstByIdIsNotNull();
        if (company != null)
            return company;
        else return new Company();}

     public Company getCompanyById(long id){

        return companyRepository.findById(id).get();
     }

     public Company updateCompany(Company company){

        Company updCompany = companyRepository.save(company);
        updCompany.getFields().forEach(field -> {
             field.setCompany(company);
             field = companyFieldsRepository.save(field);
         });
         return updCompany;
     }
}
