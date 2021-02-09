package com.alexdan.docflow.controllers;

import com.alexdan.docflow.models.Task;
import com.alexdan.docflow.models.User;
import com.alexdan.docflow.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){

        this.taskService = taskService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Set<Task> getAllTasks(@AuthenticationPrincipal User user) {

        return taskService.getAllTasks(user);
    }

    @GetMapping("/sent")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Set<Task> getCompletedSentTasks(@AuthenticationPrincipal User user) {

        return taskService.getCompletedSentTasks(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Task getTask(@PathVariable long id){

        return taskService.getTask(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task putTask(@PathVariable long id, @RequestBody Task task) {

        return taskService.saveTask(task);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Task createTask(@RequestBody Task task) {

        return taskService.saveTask(task);
    }
}
