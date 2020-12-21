package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import com.alexdan.docflow.exceptions.UserNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public User getUser(@PathVariable long id, Model model){
        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
        model.addAttribute(user);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User putUser(@PathVariable long id, @RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody User createUser(User user, BindingResult result, HttpServletResponse responce)
            throws BindException{
        if (result.hasErrors())
            throw new BindException(result);

        User savedUser = userRepository.save(user);

        responce.setHeader("Location", "/users/" + savedUser.getId());
        return savedUser;
    }

}
