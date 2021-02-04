package com.alexdan.docflow.data;

import com.alexdan.docflow.DocflowApplication;
import com.alexdan.docflow.models.Department;
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
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void CreateUserAndDepartmentTest(){

        User user1 = new User("Bob12", "Bob", "Bobov", "1234", "ingineer",
                "b@mail.com", "345678");
        userRepository.save(user1);

        User user2 = new User("Ale12", "Alex", "Ra", "1567", "technologist",
                "ф@mail.com", "345645");
        userRepository.save(user2);

        Department department = new Department("Department 230");
        departmentRepository.save(department);

        department.addEmployee(user1);
        department.addEmployee(user2);

        user1.setDepartment(department);
        user2.setDepartment(department);

       departmentRepository.save(department);
       //userRepository.save(user1);
       //userRepository.save(user2);

       User user1Find = userRepository.findByUsername(user1.getUsername());
       User user2Find = userRepository.findByUsername(user2.getUsername());
       Department departmentFind = departmentRepository.findByName(department.getName());

       assertEquals(user1Find.getId(), departmentFind.getEmployees().get(0).getId());
       assertEquals(user2Find.getId(), departmentFind.getEmployees().get(1).getId());

    }



}