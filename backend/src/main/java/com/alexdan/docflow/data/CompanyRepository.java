package com.alexdan.docflow.data;

import com.alexdan.docflow.models.company.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
