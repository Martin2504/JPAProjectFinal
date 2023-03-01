package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.DeptEmp;
import com.sparta.mg.jpaproject.model.entities.DeptEmpId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {

    @Query(value = "SELECT emp_no FROM dept_emp where " +
            "dept_no = {dept_no} AND from_date = {from_date}",nativeQuery = true)
    List<String> findByDeptEmp (String department , Date date);

    // line 15 added in EmployeeRepository

}