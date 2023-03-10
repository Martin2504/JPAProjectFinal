package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.DeptEmp;
import com.sparta.mg.jpaproject.model.entities.DeptEmpId;
import com.sparta.mg.jpaproject.model.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {
    @Query(value = "SELECT DISTINCT e FROM Employee e WHERE e.id IN (SELECT " +
        "de.id.empNo FROM DeptEmp de WHERE de.fromDate <= :date AND" +
        " de.toDate >= :date AND de.id.deptNo = :department)")
    List<Employee> findEmployeeByDeptEmp (String department , LocalDate date);
    @Query(value = "SELECT de.empNo FROM DeptEmp de WHERE de.id.deptNo = :deptNo")
    Page<Employee> getEmployeesByDeptNo(@Param("deptNo") String deptNo, Pageable page);

    @Query(value = "SELECT Count(de) FROM DeptEmp de WHERE de.id.deptNo = :deptNo AND de.fromDate <= :beforeDate AND de.toDate >= :endDate")
    int getNoOfEmpsByDeptNoWithinTimePeriod(@Param("deptNo") String deptNo, LocalDate beforeDate, LocalDate endDate);

}