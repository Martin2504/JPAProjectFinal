package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {


//get all employees in a department
    @Query(value = "SELECT AVG(salary) " +
            "FROM employees.salaries " +
            "JOIN employees.dept_emp " +
            "ON employees.salaries.emp_no = employees.dept_emp.emp_no " +
            "WHERE employees.dept_emp.dept_no = :deptNo " +
            "AND employees.dept_emp.from_date <= :date " +
            "AND employees.dept_emp.to_date >= :date" +
            "", nativeQuery = true)
    Integer getSalariesByDept(String deptNo, LocalDate date);

    @Query(value = "SELECT distinct employees.departments.dept_no " +
            "FROM employees.departments " +
            "JOIN employees.dept_emp " +
            "ON employees.departments.dept_no = employees.dept_emp.dept_no " +
            "WHERE employees.departments.dept_name = :deptName" +
            "", nativeQuery = true)
    String getDeptNo(String deptName);



    @Query(nativeQuery = true, value = "SELECT AVG(salaries.salary) " +
            "FROM salaries " +
            "JOIN employees ON salaries.emp_no = employees.emp_no " +
            "JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
            "JOIN departments ON dept_emp.dept_no = departments.dept_no " +
            "WHERE departments.dept_name = :departmentName " +
            "AND employees.gender = :gender")
    Double getAverageSalaryByDepartmentAndGender(@Param("departmentName") String departmentName, @Param("gender") String gender);


    @Query(nativeQuery = true, value = "SELECT AVG(salaries.salary) " +
            "FROM salaries " +
            "JOIN employees ON salaries.emp_no = employees.emp_no " +
            "JOIN titles ON employees.emp_no = titles.emp_no " +
            "WHERE titles.title = :jobTitle " +
            "AND employees.gender = :gender")
    Double getAverageSalaryByJobTitleAndGender(@Param("jobTitle") String jobTitle, @Param("gender") String gender);


    @Query(nativeQuery = true, value = "SELECT AVG(salaries.salary) " +
            "FROM salaries " +
            "JOIN employees ON salaries.emp_no = employees.emp_no " +
            "WHERE employees.gender = :gender")
    Double getAverageSalaryByGender(@Param("gender") String gender);

}