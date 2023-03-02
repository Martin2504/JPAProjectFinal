package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {


//get all employees in a department
    @Query(value = "SELECT * FROM employees.salaries JOIN employees.dept_emp ON employees.salaries.emp_no = employees.dept_emp.emp_no WHERE employees.dept_emp.dept_no = :deptNo", nativeQuery = true)
    List<Integer> getSalariesByDept(String deptNo);

    //get salaries of all employees in department
    @Query(value = "SELECT salary.* from salary join dept_emp on emp_no = salary.emp_no where dept.name = : dept", nativeQuery = true)
    int findActorNameByLenh(Integer length);
}