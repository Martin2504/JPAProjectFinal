package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {


 /*   @Query(nativeQuery = true, value = "SELECT AVG(salaries.salary) FROM salaries" +
            "JOIN employees ON salaries.emp_no = employees.emp_no" +
            "JOIN titles ON employees.emp_no = titles.emp_no" +
            "WHERE title = :job_title" +
            "AND ((salaries.from_date  <= :start_date AND salaries.to_date >= :start_date) OR (salaries.from_date >= :start_date AND s.from_date <= :end_date))")
    Double getAverageSalaryByTitleAndYear(LocalDate start_date, LocalDate end_date, String job_title); */


    @Query(nativeQuery = true, value = "SELECT AVG(salaries.salary) " +
            "FROM salaries " +
            "JOIN employees ON salaries.emp_no = employees.emp_no " +
            "JOIN titles ON employees.emp_no = titles.emp_no " +
            "WHERE titles.title = :jobTitle " +
            "AND ((salaries.from_date  <= :start_date AND salaries.to_date >= :start_date) OR (salaries.from_date >= :start_date AND salaries.from_date <= :end_date))")
    Double getAverageSalaryByTitleAndYear(LocalDate start_date, LocalDate end_date, String jobTitle);

}