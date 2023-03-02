package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

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

}