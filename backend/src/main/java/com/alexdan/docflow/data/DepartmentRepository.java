package com.alexdan.docflow.data;

import com.alexdan.docflow.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findByName(String name);
}
