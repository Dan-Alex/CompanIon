package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.models.User;
import com.alexdan.docflow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alexdan.docflow.exceptions.UserNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;
    UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService){

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){

        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
        return user;
    }

    @GetMapping
    public List<User> getAllUsers(){

        List<User> users = (List<User>) userRepository.findAll();
        return users;
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
}
