package com.sparta.mg.jpaproject.controllers.departmentcontrollers;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.services.ApiKeyService;
import com.sparta.mg.jpaproject.tools.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentEmployeesController {
    //Samir

    private final ApiKeyService apiKeyService;

    private final DeptEmpRepository deptEmpRepository;

    @Autowired
    public DepartmentEmployeesController(ApiKeyService apiKeyService, DeptEmpRepository deptEmpRepository) {
        this.apiKeyService = apiKeyService;
        this.deptEmpRepository = deptEmpRepository;
    }

    //    Read GET /department/{id}/employees
//
//    Given I want to know all employees from a department,
//    When I sent the correct http request given the department id,
//    Then I will receive a list of employees and status code 2xx.
    @GetMapping("/department/{deptId}/employees/{pageNumber}/{size}")
    public ResponseEntity<String> getAllEmployeeOfGivenDeptId(@PathVariable String deptId,
                                                              @PathVariable Integer pageNumber,
                                                              @PathVariable Integer size,
                                                              @RequestHeader("x-api-key") String apiKey) {
        if (!apiKeyService.validateUser(apiKey, CRUD.READ)) {
            return apiKeyService.getInvalidApiKeyResponse();
        }
        Pageable page = PageRequest.of(pageNumber, size);
        Page<Employee> pages = deptEmpRepository.getEmployeesByDeptNo(deptId, page);
        System.out.println(pages.toList().toString());

        return ResponseEntity.ok("The capabilities of this man");
    }
}
