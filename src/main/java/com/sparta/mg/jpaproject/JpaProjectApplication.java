package com.sparta.mg.jpaproject;

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaProjectApplication {
=======
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import com.sparta.mg.jpaproject.services.DepartmentService;
import com.sparta.mg.jpaproject.services.EmployeeService;
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
>>>>>>> 22df9a24e47a0b26688410dcd3ad7690193b8cf1

    public static void main(String[] args) {

        SpringApplication.run(JpaProjectApplication.class, args);
    }

<<<<<<< HEAD


=======
>>>>>>> 22df9a24e47a0b26688410dcd3ad7690193b8cf1
}
