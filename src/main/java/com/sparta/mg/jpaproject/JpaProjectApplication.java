package com.sparta.mg.jpaproject;


import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import com.sparta.mg.jpaproject.services.SalaryService;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import java.time.LocalDate;


@SpringBootApplication
public class JpaProjectApplication {
    private Logger logger = LoggerFactory.getLogger(JpaProjectApplication.class);

    Logger logger = LoggerFactory.getLogger(JpaProjectApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(JpaProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner (DeptEmpRepository deptEmpRepository){
        return args ->{
            for (Employee employee : deptEmpRepository.findEmployeeByDeptEmp("d006",LocalDate.of(1986, 2, 23))){
                logger.info(employee.toString());
            }
        };
    }


/*    @Bean
    public CommandLineRunner runner(SalaryService salaryService) {
        return args -> salaryService.getDeptSalaryAvg("d005");
    }*/

    @Bean
    public CommandLineRunner runner2(SalaryRepository salaryRepository) {
        return args -> {
            for(int s: salaryRepository.getSalariesByDept("d005")){
               logger.info(String.valueOf(s));
            }
        };
    }


}
