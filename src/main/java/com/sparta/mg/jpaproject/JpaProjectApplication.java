package com.sparta.mg.jpaproject;

import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import com.sparta.mg.jpaproject.services.DepartmentService;
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

    public static void main(String[] args) {

        SpringApplication.run(JpaProjectApplication.class, args);
    }

}
