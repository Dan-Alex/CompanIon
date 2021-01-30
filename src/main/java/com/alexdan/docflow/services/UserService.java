package com.alexdan.docflow.services;

import com.alexdan.docflow.data.DepartmentRepository;
import com.alexdan.docflow.data.RoleRepository;
import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.exceptions.UserNotFoundException;
import com.alexdan.docflow.models.Department;
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
    private DepartmentRepository departmentRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    public User updateUser(User user){
        User updUser;
        User oldUser = userRepository.findById(user.getId()).get();
        user.setPassword(oldUser.getPassword());
        if (!user.getDepartmentName().equals(oldUser.getDepartmentName())) {
            Department department = departmentRepository.findByName(user.getDepartmentName());
            user.setDepartment(department);
            updUser = userRepository.save(user);
            department.addEmployee(updUser);
            departmentRepository.save(department);
        } else {
            updUser = userRepository.save(user);
        }
        return updUser;
    }

    public User saveUser(User user){
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Department department = departmentRepository.findByName(user.getDepartmentName());
        user.setDepartment(department);
        User savedUser = userRepository.save(user);
        department.addEmployee(savedUser);
        departmentRepository.save(department);
        return savedUser;
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
        Department department = departmentRepository.findByName(user.getDepartmentName());
        department.deleteEmployee(user);
        departmentRepository.save(department);
        userRepository.deleteById(id);
    }

}
