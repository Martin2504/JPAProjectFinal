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
    //user has access to DepartmentEmployee that tracks employee schedules and department assignments
    //When user selects the desired department and date
    //Then system should display a list of all employees who worked in the selected department on the specified date
    private DeptEmpRepository deptEmpRepository ;



    @Autowired
    public DeptEmpService(DeptEmpRepository deptEmpRepository) {
        this.deptEmpRepository = deptEmpRepository;

    }

    public List<Employee> findEmployeeByDeptEmp(String department , LocalDate date) {
        if(date.isBefore(LocalDate.of(1885,1,1))) {
            throw new IllegalArgumentException();
        }
        return deptEmpRepository.findEmployeeByDeptEmp(department,date);
    }

}
