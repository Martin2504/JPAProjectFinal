package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalaryService {
    private final DeptEmpRepository deptEmpRepository;
    //Farris

    private SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository, DeptEmpRepository deptEmpRepository){
        this.salaryRepository = salaryRepository;
        this.deptEmpRepository = deptEmpRepository;
    }

    public double getAverageSalaryByTitleAndYear(LocalDate startDate, LocalDate endDate, String jobTitle) {
        return salaryRepository.getAverageSalaryByTitleAndYear(startDate, endDate, jobTitle);
    }


}
