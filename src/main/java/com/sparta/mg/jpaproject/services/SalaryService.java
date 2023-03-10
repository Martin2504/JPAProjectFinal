package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class SalaryService {

    private final DeptEmpRepository deptEmpRepository;

    private SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository, DeptEmpRepository deptEmpRepository){
        this.salaryRepository = salaryRepository;
        this.deptEmpRepository = deptEmpRepository;
    }


    public double getAverageSalaryByTitleAndYear(LocalDate startDate, LocalDate endDate, String jobTitle) {
        return salaryRepository.getAverageSalaryByTitleAndYear(startDate, endDate, jobTitle);
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

    public double getCompanySalaryAvg(){
        return salaryRepository.getCompanyAverageSalary();
    }
<<<<<<< HEAD

=======
>>>>>>> 22df9a24e47a0b26688410dcd3ad7690193b8cf1


}