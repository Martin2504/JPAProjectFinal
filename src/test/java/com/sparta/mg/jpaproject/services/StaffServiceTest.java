package com.sparta.mg.jpaproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaffServiceTest {

    @Autowired
    StaffService staffService;

    @Test
    @DisplayName("Testing staff service returns the correct number of departments")
    void testingAllDepartmentsAccountedForinGetDeptAndNoOfEmpGivenYears() {
        assertEquals(9, staffService.getDeptAndNoOfEmpGivenYears(2000, 2003).size());
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "Customer Service", "Development", "Finance", "Human Resources",
            "Marketing", "Production", "Quality Management", "Research", "Sales"
    })
    @DisplayName("Determine every department is found in method")
    public void checkEveryDepartmentFound(String departmentName) {
        assertTrue(staffService.getDeptAndNoOfEmpGivenYears(2000, 2003).containsKey(departmentName));
    }

    @Test
    @DisplayName("Testing IllegalArgumentException thrown when dates overlap")
    void testingExceptionThrownWithOverlappingParameters() {
        assertThrows(IllegalArgumentException.class, () -> staffService.getDeptAndNoOfEmpGivenYears(2001, 2000));
    }
}