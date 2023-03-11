package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.entities.Employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {

   /* @Query(value  = "SELECT t.title, AVG(s.salary) AS avg_salary" +
            "FROM employees.titles t" +
            "JOIN employees.salaries s ON t.emp_no = s.emp_no" +
            "WHERE t.title = :job_title" +
            "AND ((s.from_date  <= :start_date AND s.to_date >= :start_date) OR (s.from_date >= :start_date AND s.from_date <= :end_date))"
             ,nativeQuery = true)
    List<Employee> getAverageSalaryByTitleAndYear(LocalDate start_date, LocalDate end_date); */


   /* @Query(value = "SELECT AVG(s.salary) AS avg_salary " +
            "FROM titles t " +
            "JOIN salaries s ON t.emp_no = s.emp_no " +
            "WHERE t.title = 'Senior Engineer' " +
            "AND s.from_date <= :endDate " +
            "AND (s.to_date >= :startDate OR s.to_date IS NULL)", nativeQuery = true)
    Double getAvgSalaryByTitleAndYear(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); &/ */

 @Modifying
 @Transactional
 @Query(value = "UPDATE Department d SET d.deptName = :deptName WHERE d.id = :deptId")
 void updateDepartmentNameById(String deptName, String deptId);

 @Query(value = "SELECT d FROM Department d WHERE d.deptName = :deptName")
 Optional<Department> getDepartmentByName(String deptName);



 }