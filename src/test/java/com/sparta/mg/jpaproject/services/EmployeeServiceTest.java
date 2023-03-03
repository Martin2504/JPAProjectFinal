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