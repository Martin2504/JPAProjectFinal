package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.DeptEmp;
import com.sparta.mg.jpaproject.model.entities.DeptEmpId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId> {

    @Query(value = "SELECT de FROM DeptEmp de WHERE de.id.deptNo = :deptNo AND de.fromDate < :beforeDate AND de.toDate > :endDate")
    List<DeptEmp> getDeptEmpsByDeptNo(@Param("deptNo") String deptNo, LocalDate beforeDate, LocalDate endDate);

    @Query(value = "SELECT Count(de) FROM DeptEmp de WHERE de.id.deptNo = :deptNo AND de.fromDate <= :beforeDate AND de.toDate >= :endDate")
    int getNoOfEmpsByDeptNoWithinTimePeriod(@Param("deptNo") String deptNo, LocalDate beforeDate, LocalDate endDate);


}