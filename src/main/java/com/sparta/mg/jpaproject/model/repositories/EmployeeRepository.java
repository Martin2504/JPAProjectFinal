package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT * from employees where last_name = :lastName", nativeQuery = true)
    List<Employee> findEmployeeGivenLastName (String lastName);

    @Query(value = "SELECT Count(*) FROM employees.employees WHERE gender = 'M'", nativeQuery = true)
    int findAmountOfMales();

    @Query(value = "SELECT Count(*) FROM employees.employees WHERE gender = 'F'", nativeQuery = true)
    int findAmountOfFemales ();

}