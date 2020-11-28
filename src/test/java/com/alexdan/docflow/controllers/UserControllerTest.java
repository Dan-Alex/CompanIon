package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.models.Department;
import com.alexdan.docflow.models.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@WithMockUser(authorities = {"READ", "WRITE"})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");
        user.setId(1);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1")).
                andExpect(jsonPath("$.name").value("Bob"));

    }

    @Test
    public void getUserTestAndStatus404Test() throws Exception {

        mockMvc.perform(get("/users/42")).
                andExpect(status().isNotFound());
    }

    @Test
    public void putUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");
        user.setId(1);

        when(userRepository.save(any())).thenReturn(user);

        mockMvc.perform(put("/users/1", 1).
                            content(objectMapper.writeValueAsString(user)).
                            contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Bob")).
                andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void deleteUserAndStatus202Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");
        user.setId(1);

        mockMvc.perform(delete("/users/1", 1).
                content(objectMapper.writeValueAsString(user)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isAccepted());

    }

    @Test
    public void postUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");
        user.setId(1);

        when(userRepository.save(any())).thenReturn(user);

        mockMvc.perform(post("/users").
                content(objectMapper.writeValueAsString(user)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Bob")).
                andExpect(jsonPath("$.id").value("1"));
    }

}