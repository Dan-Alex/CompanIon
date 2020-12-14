package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.DepartmentRepository;
import com.alexdan.docflow.models.Department;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Department getDepartment(@PathVariable long id, Model model){
        Department department = departmentRepository.findById(id).
                orElseThrow(()-> new DepartmentNotFoundException(id));
        model.addAttribute(department);
        return department;
    }

    @GetMapping("/{id}/empoyees")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<User> getEmployees(@PathVariable long id, Model model){
        Department department = departmentRepository.findById(id).
                orElseThrow(()-> new DepartmentNotFoundException(id));
        List<User> employees = department.getEmployees();
        model.addAttribute(employees);
        return employees;
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

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Department createDepartment(Department department, BindingResult result, HttpServletResponse responce)
            throws BindException{
        if (result.hasErrors())
            throw new BindException(result);

        Department savedDepartment = departmentRepository.save(department);

        responce.setHeader("Location", "/users/" + savedDepartment.getId());
        return savedDepartment;
    }

}