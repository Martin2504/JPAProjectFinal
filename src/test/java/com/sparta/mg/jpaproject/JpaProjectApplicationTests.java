package com.sparta.mg.jpaproject;

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

@SpringBootTest
class JpaProjectApplicationTests {
    @Autowired
    StaffService staffService;

    @Test
    void contextLoads() {
    }
    @Nested
    @DisplayName("staff service testing")
    class staffServiceTesting {
        Map<String, Integer> deptEmpNumberMap;
        @BeforeEach
        public void setUpValues() {
            deptEmpNumberMap = staffService.getDeptAndNoOfEmpGivenYears(2000, 2003);
        }
        @Test
        @DisplayName("Testing staff service returns the correct number of departments")
        void testingAllDepartmentsAccountedForinGetDeptAndNoOfEmpGivenYears() {
            assertEquals(9, deptEmpNumberMap.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "Customer Service", "Development", "Finance", "Human Resources",
                "Marketing", "Production", "Quality Management", "Research", "Sales"
        })
        @DisplayName("Determine every department is found in method")
        public void checkEveryDepartmentFound(String departmentName) {
            assertTrue(deptEmpNumberMap.containsKey(departmentName));
        }
    }
}
