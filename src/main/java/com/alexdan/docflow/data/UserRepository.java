package com.alexdan.docflow.data;

import com.alexdan.docflow.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
