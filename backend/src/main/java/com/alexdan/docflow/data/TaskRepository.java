package com.alexdan.docflow.data;

import com.alexdan.docflow.models.Task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TaskRepository extends CrudRepository<Task, Long> {

@Query(value = "SELECT * FROM tasks WHERE to_whom_user_id = :user_id ORDER BY id DESC LIMIT 3", nativeQuery =true)
public Set<Task> findLastThreeTasks(@Param("user_id") Long userId);

@Query(value = "SELECT * FROM tasks WHERE to_whom_user_id = :user_id ORDER BY id", nativeQuery =true)
public Set<Task> findAllTasks(@Param("user_id") Long userId);

}
