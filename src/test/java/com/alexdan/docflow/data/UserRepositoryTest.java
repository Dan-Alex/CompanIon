package com.alexdan.docflow.data;

import com.alexdan.docflow.DocflowApplication;
import com.alexdan.docflow.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocflowApplication.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void CreateAndFindTest(){
        User user = new User("Bob", "1234", "ingineer", "b@mail.com", "345678");
        User userSave = userRepository.save(user);
        User userFind = userRepository.findById(userSave.getId()).get();
        assertNotNull(userFind);
        assertEquals(userFind.getId(),userSave.getId());
        assertEquals(userFind.getName(),userSave.getName());
    }

}