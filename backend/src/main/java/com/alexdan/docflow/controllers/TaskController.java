package com.alexdan.docflow.controllers;

import com.alexdan.docflow.models.Task;
import com.alexdan.docflow.models.User;
import com.alexdan.docflow.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Task> getAllTasks(@AuthenticationPrincipal User user) {

        return taskService.getAllIncomingTasks(user);
    }

    @GetMapping("/sent")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Task> getAllOutgoingTasks(@AuthenticationPrincipal User user) {

        return taskService.getAllOutgoingTasks(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Task getTask(@PathVariable long id){

        return taskService.getTask(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Task putTask(@PathVariable long id, @RequestBody Task task) {

        return taskService.saveTask(task);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public @ResponseBody Task createTask(@RequestBody Task task) {

        return taskService.saveTask(task);
    }
}
