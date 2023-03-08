package com.sparta.mg.jpaproject.controllers.departmentcontrollers;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
    //Samir

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping(value = "/department")
    public String createNewDepartment(@RequestParam String departmentName) {
        Department department = new Department();
        department.setDeptName(departmentName);

        departmentRepository.save(department);
        return "Department " + departmentName + " successfully added";
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PatchMapping("/department/{deptId}")
    public String updateDepartmentNameWithId(@PathVariable String deptId, @RequestParam String deptName) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (department.isPresent()) {
            departmentRepository.updateDepartmentNameById(deptName, deptId);
            return "Successfully updated";
        } else {
            return "Unsuccessfully updated";
        }
    }

    @DeleteMapping("/department/{deptId}")
    public String deleteDepartmentWithId(@PathVariable String deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);

        if (department.isPresent()) {
            departmentRepository.deleteById(deptId);
            return "Department successfully removed";
        } else {
            return "Department doesn't exist";
        }
    }


}
