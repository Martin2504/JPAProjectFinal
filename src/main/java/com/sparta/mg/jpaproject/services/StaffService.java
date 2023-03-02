package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {
    // Samir
    //As a user I would like to be
    // given the number of staff who
    // worked for each department
    // over a given time period
    // from the start of the year
    // to the end of the year

    private final EmployeeRepository employeeRepository;
    private final DeptEmpRepository deptEmpRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public StaffService(EmployeeRepository employeeRepository, DeptEmpRepository deptEmpRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.deptEmpRepository = deptEmpRepository;
        this.departmentRepository = departmentRepository;
    }

    private Map<String, Integer> getDeptAndNoOfEmpGivenTimePeriod(LocalDate start_Date, LocalDate end_Date) {
        Map<String, Integer> departmentAndNumbers = new HashMap<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department: departments) {
            String keyValue = department.getDeptName();
            int totalNumber = 0;
            totalNumber = deptEmpRepository.getNoOfEmpsByDeptNoWithinTimePeriod(department.getId(), start_Date, end_Date);
            departmentAndNumbers.put(department.getDeptName(), totalNumber);
        }
        return departmentAndNumbers;
    }

    public Map<String, Integer> getDeptAndNoOfEmpGivenYears(int start_Year, int end_Year) throws IllegalArgumentException{
        if (start_Year > end_Year) {
            throw new IllegalArgumentException();
        }
        return getDeptAndNoOfEmpGivenTimePeriod(LocalDate.of(start_Year, 1, 1), LocalDate.of(end_Year, 12, 31));
    }


}
