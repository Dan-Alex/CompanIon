package com.alexdan.companion.data;

import com.alexdan.companion.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

   public Department findByName(String name);
}
