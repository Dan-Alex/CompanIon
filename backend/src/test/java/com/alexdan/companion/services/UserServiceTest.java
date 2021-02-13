package com.alexdan.companion.services;

import com.alexdan.companion.CompanionApplication;
import com.alexdan.companion.exceptions.UserNotFoundException;
import com.alexdan.companion.models.User;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanionApplication.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void SaveAndGetUser() {

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");

        User userSave = userService.saveUser(user);
        User userFind = userService.getUser(userSave.getId());

        assertNotNull(userFind);
        assertEquals(userFind.getId(),userSave.getId());
        assertEquals(userFind.getName(),userSave.getName());
    }

    @Test
    void updateUser() {

        User user = new User("Alex2", "A", "B", "1234", "ingineer",
                "a@mail.com", "341234");
        User userSave = userService.saveUser(user);
        String newPosition = "инженер 2-ой категории";
        user.setPosition(newPosition);

        userService.saveUser(user);
        User userUpdate = userService.getUser(userSave.getId());

        assertEquals(userSave.getId(), userUpdate.getId());
        assertEquals(newPosition, userUpdate.getPosition());
    }

    @Test
    void deleteUser() throws UserNotFoundException {

        User user = new User("QWERT", "mike", "ivanov", "1234", "ingineer",
                "a@mail.com", "341234");

        User userSave = userService.saveUser(user);
        userService.deleteUser(userSave.getId());
        assertThrows(UserNotFoundException.class, () -> {
                                                        userService.getUser(userSave.getId());;
                                                    });

    }
}