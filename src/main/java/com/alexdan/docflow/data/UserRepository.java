package com.alexdan.docflow.data;

import com.alexdan.docflow.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
    User findByUsername(String username);
    List<User> findAllByNameAndSurname(String name, String surname);
}
