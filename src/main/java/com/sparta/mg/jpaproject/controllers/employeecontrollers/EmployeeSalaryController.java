package com.sparta.mg.jpaproject.controllers.employeecontrollers;

    //Ali

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import com.sparta.mg.jpaproject.services.ApiKeyService;
import com.sparta.mg.jpaproject.tools.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeSalaryController {
    // Ali

    private SalaryRepository salaryRepository;
    private ApiKeyService apiKeyService;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSalaryController(SalaryRepository salaryRepository, ApiKeyService apiKeyService, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.apiKeyService = apiKeyService;
        this.employeeRepository = employeeRepository;
    }

    //Create Method
    @PostMapping("/employee/salary/{empNo}")
    public ResponseEntity<String> createSalary(
            @PathVariable Integer empNo,
            @RequestParam("salary") int salary,
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            @RequestHeader("x-api-key") String apiKey) {

        if (!apiKeyService.validateUser(apiKey, CRUD.CREATE)) {
            return apiKeyService.getInvalidApiKeyResponse();
        }

        Optional<Employee> emp1 = employeeRepository.findById(empNo);
        if (emp1.isPresent()) {
            Salary newSalary = new Salary();
            newSalary.setSalary(salary);
            newSalary.setToDate(toDate);
            SalaryId salaryId = new SalaryId();
            salaryId.setEmpNo(empNo);
            salaryId.setFromDate(fromDate);
            newSalary.setId(salaryId);
            newSalary.setEmpNo(emp1.get());
            salaryRepository.save(newSalary);
            return ResponseEntity.ok("Salary created for employee " + empNo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    //Read Method
    @GetMapping("/employee/salary/{empNo}")
    public ResponseEntity<String> getSalaryByEmpNo(
            @PathVariable Integer empNo,
            @RequestHeader("x-api-key") String apiKey) {

        if (!apiKeyService.validateUser(apiKey, CRUD.READ)) {
            return apiKeyService.getInvalidApiKeyResponse();
        }
        Optional<Employee> emp1 = employeeRepository.findById(empNo);
        if (emp1.isPresent()) {
            List<Salary> salaries = salaryRepository.findSalariesByEmpNo(emp1.get());
            List<Integer> salaryValues = new ArrayList<>();
            for (Salary salary : salaries) {
                salaryValues.add(salary.getSalary());
            }
            return ResponseEntity.ok(salaryValues.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    //Update Method
    @PatchMapping("/employee/salary/{empNo}/{fromDate}")
    public ResponseEntity<String> updateSalary(
            @PathVariable("empNo") int empNo,
            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam int salary,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            @RequestHeader("x-api-key") String apiKey) {

        if (!apiKeyService.validateUser(apiKey, CRUD.UPDATE)) {
            return apiKeyService.getInvalidApiKeyResponse();
        }
        Optional<Salary> existingSalary = salaryRepository.findSalariesByEmpNoAndFromDate(empNo, fromDate);
        if (existingSalary.isPresent()) {
            Salary updatedSalary = existingSalary.get();
            updatedSalary.setSalary(salary);
            updatedSalary.setToDate(toDate);
            salaryRepository.save(updatedSalary);
            return new ResponseEntity<>("The salary for emp_no " + empNo + " has been updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The salary for emp_no " + empNo + " does not exist", HttpStatus.NOT_FOUND);
        }
    }


    //Delete Method
    @DeleteMapping("/employee/salaries/{empNo}")
    public ResponseEntity<String> deleteSalary(
            @PathVariable("empNo") int empNo,
            @RequestHeader("x-api-key") String apiKey) {

        if (!apiKeyService.validateUser(apiKey, CRUD.DELETE)) {
            return apiKeyService.getInvalidApiKeyResponse();
        }
        Optional<Employee> emp1 = employeeRepository.findById(empNo);
        List<Salary> salaries = salaryRepository.findSalariesByEmpNo(emp1.get());
        if (emp1.isPresent()) {
            for (Salary salary : salaries) {
                salaryRepository.delete(salary);
            }
            String message = "All salaries for employee " + empNo + " has been removed";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}



