package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeptEmpService {
    // Maria
    private DeptEmpRepository deptEmpRepository ;
    private final EmployeeRepository employeeRepository;



    @Autowired
    public DeptEmpService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getDeptEmp (String department , LocalDate date){
        List<Employee> employee = employeeRepository.findByDeptEmp (department , date);
        return employee ;
    }


}
