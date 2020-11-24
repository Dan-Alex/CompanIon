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

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable long id, Model model){
        model.addAttribute(userRepository.findById());
        return "users/viewUser";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public putUser(@PathVariable long id, User user){
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User createUser(User user, BindingResult result, HttpServletResponse responce){
        if (result.hasErrors())
            throw new BindException(result);

        User saveUser = userRepository.save(user);

        responce.setHeader("Location", "/users/" + saveUser.getId());
        return saveUser;
    }

}
