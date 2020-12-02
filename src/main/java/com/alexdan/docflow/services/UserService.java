package com.alexdan.docflow.services;

import com.alexdan.docflow.data.RoleRepository;
import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.models.Role;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    public User saveUser(User user){
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
