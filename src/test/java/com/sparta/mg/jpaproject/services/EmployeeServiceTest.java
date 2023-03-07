package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Test
    @DisplayName("Testing if list is populated after a last name is passed")
    public void isListPopulatedTest () {
        assertEquals(167,employeeService.findEmployeesByLastName("Simmel").size());
    }

    @Test
    @DisplayName("Testing if IllegalArgumentException is thrown when null is passed")
    public void isExceptionThrownUponNullPass () {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findEmployeesByLastName(null));
    }

    @Test
    @DisplayName("Testing if IllegalArgumentException is thrown when empty string")
    public void isExceptionThrownUponEmptyPass () {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findEmployeesByLastName(""));
    }

    @Test
    public void testGetEmployeesWithSalaryOver_NoReturn() {
        List<Employee> employees = employeeService.getEmployeesWithSalaryOver(200000);
        assertEquals(0, employees.size());
    }

    @Test
    public void testGetEmployeesWithSalaryOver(){
        List<Employee> employees = employeeService.getEmployeesWithSalaryOver(155000);
        assertEquals(5, employees.size());
    }
    
    @Test
    @DisplayName("Testing genderComparison() method returns expected")
    public void genderComparisonTest() {
        String[] each = employeeService.genderComparison().split(" ");
//        for (String s : each) {
//            assertNotNull(s);
//        }
        assertAll(
                "Group of assertions",
                () -> assertNotNull(each[0]),
                () -> assertNotNull(each[1]),
                () -> assertNotNull(each[2]),
                () -> assertNotNull(each[3]),
                () -> assertNotNull(each[4])
                );

//        assertEquals("Males: 179973 || Females: 120051", employeeService.genderComparison());
    }

}