package com.alexdan.docflow.data;

import com.alexdan.docflow.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
