package com.alexdan.docflow.services;

import com.alexdan.docflow.data.DepartmentRepository;
import com.alexdan.docflow.data.DocumentRepository;
import com.alexdan.docflow.data.UserRepository;
import com.alexdan.docflow.exceptions.UserNotFoundException;
import com.alexdan.docflow.models.Department;
import com.alexdan.docflow.models.Document;
import com.alexdan.docflow.models.Role;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository,
                       DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    public User getUser(long id){

        return userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
    }

    public List<User> getAllUsers(){

        return (List<User>) userRepository.findAll();
    }

    public User updateUser(User user){

        User updUser;
        User oldUser = userRepository.findById(user.getId()).get();
        if ((!user.getDepartmentName().equals(oldUser.getDepartmentName())) || (oldUser.getDepartment() == null)) {
            updUser = save(user);
        } else {
            updUser = userRepository.save(user);
        }
        return updUser;
    }

    public User saveUser(User user){

        user.setRoles(Collections.singleton(new Role(1L,"ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return save(user);
    }

    public void deleteUser(Long id){

        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
        Department department = departmentRepository.findByName(user.getDepartmentName());
        department.deleteEmployee(user);
        departmentRepository.save(department);
        userRepository.deleteById(id);
    }

    public User save(User user) {

        Department department = departmentRepository.findByName(user.getDepartmentName());
        user.setDepartment(department);
        User savedUser = userRepository.save(user);
        department.addEmployee(savedUser);
        departmentRepository.save(department);
        return savedUser;
    }

    public List<Document> getUsersDocuments(long id){

        return this.getUser(id).getDocuments();
    }

    public Document addFile(long id, Document document){
        User user = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException(id));
        document.setUser(user);
        Document savedDocument = documentRepository.save(document);
        user.addDocument(savedDocument );
        userRepository.save(user);
        return savedDocument ;
    }

}
