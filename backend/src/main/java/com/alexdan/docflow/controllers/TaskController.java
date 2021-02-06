package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.DocumentRepository;
import com.alexdan.docflow.data.TaskRepository;
import com.alexdan.docflow.exceptions.TaskNotFoundException;
import com.alexdan.docflow.models.Task;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskRepository taskRepository;
    DocumentRepository documentRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, DocumentRepository documentRepository){

        this.taskRepository = taskRepository;
        this.documentRepository= documentRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Set<Task> getAllTasks(@AuthenticationPrincipal User user) {

        return taskRepository.findAllTasks(user.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Task getTask(@PathVariable long id){

        Task task = taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException(id));
        return task;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task putTask(@PathVariable long id, @RequestBody Task task) {

        return taskRepository.save(task);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Task createTask(@RequestBody Task task) {

        Task savedTask = taskRepository.save(task);
        task.getDocuments().forEach(document -> {
            document.setTask(savedTask);
            documentRepository.save(document);
        });
        return savedTask;
    }
}
