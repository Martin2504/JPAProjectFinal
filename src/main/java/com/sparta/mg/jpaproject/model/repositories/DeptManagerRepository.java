package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.DeptManager;
import com.sparta.mg.jpaproject.model.entities.DeptManagerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerId> {

    List<DeptManager> getDeptManagersById_DeptNo(String deptNo);
}