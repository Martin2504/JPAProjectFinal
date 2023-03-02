package com.sparta.mg.jpaproject;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.services.DeptEmpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.sparta.mg.jpaproject.services.StaffService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JpaProjectApplicationTests {
    @Autowired
    DeptEmpService deptEmpService;


    @Test
    void contextLoads() {
    }
    @Nested
    @DisplayName("maria is testing")
    class mariaTesting {


        @Nested
        @DisplayName("User selects a department and date")
        class DepartmentAndDate {

            private String department;
            private LocalDate date;
            private List<Employee> employee;

            @BeforeEach
            public void setUpDeptEmp() {
                department = "d006";
                date = LocalDate.of(1986, 2, 23);
            }
        }

        @Nested
        @DisplayName("When user has access to department")
        class UserHasAccess {
            @ParameterizedTest
            @ValueSource(strings = {"d006", "d005"})
            @DisplayName("Then display list of employees who worked in the department on specified date")
            public void acceptedTestEmployeeByDeptEmp(String department) {
                LocalDate date = LocalDate.of(1986, 2, 23);
                List<Employee> employee = deptEmpService.findEmployeeByDeptEmp(department, date);
                assertNotNull(employee);
            }
        }

        @Nested
        @DisplayName("When user does not have access to department")
        class UserDoesNotHaveAccess {
            @Test
            @DisplayName("Then return Try again")
            public void rejectedTestEmployeeByDeptEmp() {
                LocalDate date = LocalDate.of(1700, 2, 23);
                assertThrows(IllegalArgumentException.class, () -> deptEmpService.findEmployeeByDeptEmp("d004", date));
            }

        }
    }
}
