package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalaryService  {

    private SalaryRepository salaryRepository;

    private DeptEmpRepository deptEmpRepository;

    public SalaryService(SalaryRepository salaryRepository, DeptEmpRepository deptEmpRepository){
        this.salaryRepository = salaryRepository;
        this.deptEmpRepository = deptEmpRepository;
    }

    public String getDeptNo(String department){
        return salaryRepository.getDeptNo(department);
    }
    public String getDeptSalaryAvg(String deptName, LocalDate localDate){
        return String.valueOf(deptName + ": " +
                salaryRepository.getSalariesByDept(getDeptNo(deptName), localDate)) ;
    }

    public String compareGetDeptSalaryAvg(String deptName, String deptName2, LocalDate localDate){
        return String.valueOf(deptName + ": " +
                salaryRepository.getSalariesByDept(getDeptNo(deptName), localDate) +" " + deptName2 + ": " + salaryRepository.getSalariesByDept(getDeptNo(deptName2), localDate)) ;
    }




}
