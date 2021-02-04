package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.DepartmentRepository;
import com.alexdan.docflow.models.Department;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alexdan.docflow.exceptions.DepartmentNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository){

        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Department> getAllDepartments(){

        return (List<Department>) departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Department getDepartment(@PathVariable long id){

        Department department = departmentRepository.findById(id).
                orElseThrow(()-> new DepartmentNotFoundException(id));
        return department;
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Department putDepartment(@PathVariable long id, @RequestBody Department department){

        return departmentRepository.save(department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDepartment(@PathVariable long id){

        departmentRepository.deleteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Department createUser(@RequestBody Department department) {

        return departmentRepository.save(department);
    }
}