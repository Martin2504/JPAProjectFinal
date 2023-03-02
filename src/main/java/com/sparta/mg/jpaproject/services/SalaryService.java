package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService  {

    private SalaryRepository salaryRepository;

    private DeptEmpRepository deptEmpRepository;

    public SalaryService(SalaryRepository salaryRepository, DeptEmpRepository deptEmpRepository){
        this.salaryRepository = salaryRepository;
        this.deptEmpRepository = deptEmpRepository;
    }

    public void getDeptSalaryAvg(String deptNo){
        List<Integer> sList = salaryRepository.getSalariesByDept(deptNo);
        for(int s: sList){
            System.out.println(s);
        }
    }




}
