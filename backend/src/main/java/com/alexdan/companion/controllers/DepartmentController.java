package com.alexdan.companion.controllers;

import com.alexdan.companion.models.Department;
import com.alexdan.companion.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){

        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments(){

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable long id){

        return departmentService.getDepartment(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Department putDepartment(@PathVariable long id, @RequestBody Department department){

        return departmentService.saveDepartment(department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDepartment(@PathVariable long id){

        departmentService.deleteDepartment(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Department createUser(@RequestBody Department department) {

        return departmentService.saveDepartment(department);
    }
}