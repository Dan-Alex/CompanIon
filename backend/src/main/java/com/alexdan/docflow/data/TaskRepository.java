package com.alexdan.docflow.data;

import com.alexdan.docflow.models.Task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
