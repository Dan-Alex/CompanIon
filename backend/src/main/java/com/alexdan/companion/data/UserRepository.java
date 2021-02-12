package com.alexdan.companion.data;

import com.alexdan.companion.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAllByNameAndSurname(String name, String surname);
}
