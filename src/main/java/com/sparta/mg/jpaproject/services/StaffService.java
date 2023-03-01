package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Map<String, Integer> getDeptAndNoOfEmpGivenDeptIdAndTimePeriod(int dept_id, Date start_Date, Date end_Date) {
        return null;
    }

    public Map<String, Integer> getDeptAndNoOfEmpGivenDeptNameAndTimePeriod(String dept_Name, Date start_Date, Date end_Date) {
        return null;
    }

    public Map<String, Integer> getDeptAndNoOfEmpGivenDeptIdAndYears(int dept_id, int start_Year, int end_Year) {
        return null;
    }

    public Map<String, Integer> getDeptAndNoOfEmpGivenDeptNameAndYears(String dept_Name, int start_Year, int end_Year) {
        return null;
    }


}
