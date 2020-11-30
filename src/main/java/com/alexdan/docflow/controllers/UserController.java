package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import com.alexdan.docflow.exceptions.UserNotFoundException;


@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id, Model model){
        User user = userRepository.findById(id).
                                   orElseThrow(()-> new UserNotFoundException(id));
        model.addAttribute(user);
        return user;
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable long id, @RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }

    @PostMapping
    public @ResponseBody User createUser(User user, BindingResult result, HttpServletResponse responce)
                                        throws BindException{
        if (result.hasErrors())
            throw new BindException(result);

        User savedUser = userRepository.save(user);

        responce.setHeader("Location", "/users/" + savedUser.getId());
        return savedUser;
    }

}
