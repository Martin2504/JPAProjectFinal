package com.sparta.mg.jpaproject.controllers.employeecontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<String> setEmployee(@RequestBody Employee employee) {

        if (employeeRepository.findEmployeeById(employee.getId()) == null) {
            employeeRepository.save(employee);
            return new ResponseEntity<>(
                    "Employee " + employee + " added.",
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Employee with ID " + employee.getId() + " already exists.",
                    new HttpHeaders(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<String> getEmployeeById(@PathVariable Integer id) {

        if (employeeRepository.findEmployeeById(id) == null) {
            return new ResponseEntity<>(
                   "Employee with ID " + id + " doesn't exist.",
                   new HttpHeaders(),
                   HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(
                    "Employee found: " + employeeRepository.findEmployeeById(id),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/employee")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {

        if (employeeRepository.findEmployeeById(employee.getId()) == null) {
            return new ResponseEntity<>(
                    "Employee with ID " + employee.getId() + " doesn't exist.",
                    new HttpHeaders(),
                    HttpStatus.NOT_FOUND);
        } else {
            employeeRepository.save(employee);
            return new ResponseEntity<>(
                    "Employee with ID " + employee.getId() + " has been updated.",
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {

        if (employeeRepository.findEmployeeById(id) != null) {
            employeeRepository.delete(employeeRepository.findEmployeeById(id));
            return new ResponseEntity<>(
                    "Employee with ID " + id + " has been deleted.",
                    new HttpHeaders(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Employee with ID " + id + " doesn't exist.",
                    new HttpHeaders(),
                    HttpStatus.NOT_FOUND);
        }
    }
}
