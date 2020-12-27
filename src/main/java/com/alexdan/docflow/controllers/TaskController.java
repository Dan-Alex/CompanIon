package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.TaskRepository;
import com.alexdan.docflow.exceptions.TaskNotFoundException;
import com.alexdan.docflow.models.Task;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks(@AuthenticationPrincipal User user){
        return (List<Task>) user.getTasks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Task getTask(@PathVariable long id){
        return taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
}
