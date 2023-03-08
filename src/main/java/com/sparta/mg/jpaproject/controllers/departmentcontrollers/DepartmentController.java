package com.sparta.mg.jpaproject.controllers.departmentcontrollers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public class DepartmentController {
    //Samir

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
    //Samir

    private final DepartmentRepository departmentRepository;

    private ObjectMapper mapper;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, ObjectMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @PostMapping(value = "/department")
    public ResponseEntity<String> createNewDepartment(@RequestParam String departmentName) {
        Department department = new Department();
        department.setDeptName(departmentName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        try {
            departmentRepository.save(department);
            ResponseEntity<String> response = new ResponseEntity<>(
                    "{\"message\":\"The department of name " + departmentName + " has been saved\"}",
                    httpHeaders,
                    HttpStatus.CREATED
            );
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<String> failureResponse = new ResponseEntity<>(
                "{\"message\":\"The department of name " + departmentName + " was not saved\"}",
                httpHeaders,
                HttpStatus.BAD_GATEWAY //ToDo: Find correct status
        );
        return failureResponse;
    }

    @GetMapping("/departments")
    public ResponseEntity<String> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (departments.size() > 0) {
            try {
                ResponseEntity<String> response = new ResponseEntity<>(
                        mapper.writeValueAsString(departments),
                        httpHeaders,
                        HttpStatus.OK
                );
                return response;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<String> noDepartmentsExistResponse = new ResponseEntity<>(
                "{\"message\":\"There are no departments saved in the database\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND // ToDo: Need find the correct HttpStatus response
        );
        return noDepartmentsExistResponse;
    }

    @PatchMapping("/department/{deptId}")
    public ResponseEntity<String> updateDepartmentNameWithId(@PathVariable String deptId, @RequestParam String deptName) {
        Optional<Department> department = departmentRepository.findById(deptId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (department.isPresent()) {
            departmentRepository.updateDepartmentNameById(deptName, deptId);
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(
                        mapper.writeValueAsString("{\"message\":\"The department of id "
                                + deptId + " has been updated with the department name of "
                                + deptName + "\"}"),
                        httpHeaders,
                        HttpStatus.OK
                );
                return response;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        ResponseEntity<String> noDepartmentExistResponse = new ResponseEntity<>(
                "{\"message\":\"The department of id " + deptId + " doesn't exist in the database\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND // ToDo: Need find the correct HttpStatus response
        );
        return noDepartmentExistResponse;
    }

    @DeleteMapping("/department/{deptId}")
    public ResponseEntity<String> deleteDepartmentWithId(@PathVariable String deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (department.isPresent()) {
            departmentRepository.deleteById(deptId);
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(
                        mapper.writeValueAsString("{\"message\":\"The department with id "
                                + deptId + " has been deleted\"}"),
                        httpHeaders,
                        HttpStatus.OK
                );
                return response;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        ResponseEntity<String> noDepartmentExistResponse = new ResponseEntity<>(
                "{\"message\":\"The department of id " + deptId + " doesn't exist in the database\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND // ToDo: Need find the correct HttpStatus response
        );
        return noDepartmentExistResponse;
    }
}
}
