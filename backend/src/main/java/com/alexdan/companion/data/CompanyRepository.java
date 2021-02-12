package com.alexdan.companion.data;

import com.alexdan.companion.models.company.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    public Company findFirstByIdIsNotNull();
}
