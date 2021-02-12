package com.alexdan.companion.data;

import com.alexdan.companion.models.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
