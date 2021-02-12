package com.alexdan.companion.controllers;

import com.alexdan.companion.models.Task;
import com.alexdan.companion.models.User;
import com.alexdan.companion.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/new")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Task> getNewTasks(@AuthenticationPrincipal User user) {

        return taskService.getNewIncomingTasks(user);
    }

    @GetMapping("/performed")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Task> getPerformedTasks(@AuthenticationPrincipal User user) {

        return taskService.getPerformedIncomingTasks(user);
    }

    @GetMapping("/completed")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Task> getCompletedTasks(@AuthenticationPrincipal User user) {

        return taskService.getCompletedIncomingTasks(user);
    }

    @GetMapping("/outgoing")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Task> getCompletedOutgoingTasks(@AuthenticationPrincipal User user) {

        return taskService.getCompletedOutgoingTasks(user);
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
