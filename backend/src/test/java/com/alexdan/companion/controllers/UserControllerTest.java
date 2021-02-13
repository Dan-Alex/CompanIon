package com.alexdan.companion.controllers;

import com.alexdan.companion.CompanionApplication;
import com.alexdan.companion.exceptions.UserNotFoundException;
import com.alexdan.companion.models.User;
import com.alexdan.companion.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanionApplication.class)
@WithMockUser(roles = {"ADMIN"})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Bob"));

    }

   @Test
    public void getUserTestAndStatus404Test() throws Exception {

        when(userService.getUser(42L)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/42")).
                andExpect(status().isNotFound());
    }

    @Test
    public void putUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        when(userService.updateUser(any())).thenReturn(user);

        mockMvc.perform(put("/users/2").
                content(objectMapper.writeValueAsString(user)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    public void deleteUserAndStatus202Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        mockMvc.perform(delete("/users/1", 1).
                content(objectMapper.writeValueAsString(user)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isAccepted());

    }

    @Test
    public void postUserAndStatus200Test() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        when(userService.saveUser(any())).thenReturn(user);

        mockMvc.perform(post("/users").
                content(objectMapper.writeValueAsString(user)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    @WithMockUser("hasRole('USER')")
    public void postUserWhenRoleUserAndStatus() throws Exception {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        when(userService.saveUser(any())).thenReturn(user);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/users").
                    content(objectMapper.writeValueAsString(user)).
                    contentType(MediaType.APPLICATION_JSON));
        });
    }
}