package com.alexdan.docflow.services;

import com.alexdan.docflow.data.DepartmentRepository;
import com.alexdan.docflow.exceptions.DepartmentNotFoundException;
import com.alexdan.docflow.models.Department;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserService userService;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, UserService userService) {

        this.departmentRepository = departmentRepository;
        this.userService = userService;
    }

    public List<Department> getAllDepartments(){

        return (List<Department>) departmentRepository.findAll();
    }

    public Department getDepartment(long id){

        return departmentRepository.findById(id).
                orElseThrow(()-> new DepartmentNotFoundException(id));
    }

    public Department saveDepartment(Department department){

        Department savedDepartment = departmentRepository.save(department);
        User chief = department.getChief();
        chief.setDepartmentName(savedDepartment.getName());
        userService.updateUser(chief);
        return savedDepartment;
    }

    public void deleteDepartment(long id){

        Department department = this.getDepartment(id);
        department.getEmployees().forEach(employee -> {
                                                User user = userService.getUser(employee.getId());
                                                user.setDepartment(null);
                                                user.setDepartmentName("");
                                        });
        departmentRepository.deleteById(id);
    }
}
