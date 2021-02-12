package com.alexdan.companion.data;

import com.alexdan.companion.DocflowApplication;
import com.alexdan.companion.models.User;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocflowApplication.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void CreateAndFindTest(){

        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                                "b@mail.com", "345678");
        User userSave = userRepository.save(user);
        User userFind = userRepository.findById(userSave.getId()).get();

        assertNotNull(userFind);
        assertEquals(userFind.getId(),userSave.getId());
        assertEquals(userFind.getName(),userSave.getName());

        List<User> userFindByName = userRepository.findAllByNameAndSurname(userSave.getName(),userSave.getSurname() );

        assertEquals(userFindByName.get(0).getId(),userSave.getId());
        assertEquals(userRepository.findById((long) 10), Optional.empty());

    }

    @Test
    public void UpdateTest(){

        User user = new User("Alex2", "A", "B", "1234", "ingineer",
                                "a@mail.com", "341234");
        User userSave = userRepository.save(user);
        String newPosition = "инженер 2-ой категории";
        user.setPosition(newPosition);

        userRepository.save(user);
        User userUpdate = userRepository.findByUsername(user.getUsername());

        assertEquals(userSave.getId(), userUpdate.getId());
        assertEquals(newPosition, userUpdate.getPosition());

    }

    @Test
    public void DeleteTest(){

        User user = new User("QWERT", "mike", "ivanov", "1234", "ingineer",
                                "a@mail.com", "341234");

        User userSave = userRepository.save(user);
        userRepository.delete(user);

        assertEquals(userRepository.findById(userSave.getId()), Optional.empty());

    }

}