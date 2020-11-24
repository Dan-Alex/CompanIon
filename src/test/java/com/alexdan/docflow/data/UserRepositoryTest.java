package com.alexdan.docflow.data;

import com.alexdan.docflow.DocflowApplication;
import com.alexdan.docflow.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocflowApplication.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void CreateAndFindTest(){
        User user = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                                "b@mail.com", "345678","design department");
        User userSave = userRepository.save(user);
        User userFind = userRepository.findById(userSave.getId());
        assertNotNull(userFind);
        assertEquals(userFind.getId(),userSave.getId());
        assertEquals(userFind.getName(),userSave.getName());

        List<User> userFindByName = userRepository.findAllByNameAndSurname(userSave.getName(),userSave.getSurname() );
        assertEquals(userFindByName.get(0).getId(),userSave.getId());
        assertNull(userRepository.findById(10000));
    }

    @Test
    public void UpdateTest(){
        User user = new User("Alex2", "A", "B", "1234", "ingineer",
                                "a@mail.com", "341234","design department");
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
                                "a@mail.com", "341234","design department");
        User userSave = userRepository.save(user);
        userRepository.delete(user);
        assertNull(userRepository.findById(userSave.getId()));
    }



}