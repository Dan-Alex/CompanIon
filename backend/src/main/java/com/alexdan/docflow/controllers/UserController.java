package com.alexdan.docflow.controllers;

import com.alexdan.docflow.models.User;
import com.alexdan.docflow.models.Document;
import com.alexdan.docflow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){

        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User putUser(@PathVariable long id, @RequestBody User user){

        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable long id){

        userService.deleteUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody User createUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    @GetMapping("/{id}/files")
    public List<Document> getUserFiles(@PathVariable long id){

        return userService.getUsersDocuments(id);
    }

    @PostMapping(value="/{id}/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Document addFile(@PathVariable long id, @RequestBody Document document){

        return userService.addFile(id, document);
    }
}
