package com.sparta.mg.jpaproject.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GenericGenerator(name = "dept_no", strategy = "com.sparta.mg.jpaproject.model.generators.StringSequenceIdentifier")
    @GeneratedValue(generator = "dept_no")
    @Column(name = "dept_no", nullable = false, length = 4)
    private String id;

    @Column(name = "dept_name", nullable = false, length = 40)
    private String deptName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}