package com.alexdan.companion.services;

import com.alexdan.companion.data.DepartmentRepository;
import com.alexdan.companion.exceptions.DepartmentNotFoundException;
import com.alexdan.companion.models.Department;
import com.alexdan.companion.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Department> departments = (List<Department>) departmentRepository.findAll();
        return departments.stream()
                .sorted(Comparator.comparing(Department::getName))
                .collect(Collectors.toList());
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
