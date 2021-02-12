package com.alexdan.companion.controllers;

import com.alexdan.companion.models.User;
import com.alexdan.companion.models.Document;
import com.alexdan.companion.models.json.JsonViews;
import com.alexdan.companion.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(JsonViews.Public.class)
    public User getUser(@PathVariable long id){

        return userService.getUser(id);
    }

    @GetMapping
    @JsonView(JsonViews.Public.class)
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @JsonView(JsonViews.Public.class)
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
    @JsonView(JsonViews.FullInfo.class)
    public @ResponseBody User createUser(@RequestBody User user) {

        return userService.saveUser(user);
    }

    @GetMapping("/{id}/files")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Document> getUserFiles(@PathVariable long id){

        return userService.getUsersDocuments(id);
    }

    @PostMapping(value="/{id}/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Document addFile(@PathVariable long id, @RequestBody Document document){

        return userService.addFile(id, document);
    }
}
