package com.sparta.mg.jpaproject.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryServiceTest {
    @Autowired
    private SalaryService salaryService;

    @Test
    @DisplayName("Get the Department number from a department name")
    public void getDeptNo() {
        assertEquals("d009", salaryService.getDeptNo("Customer Service"));
    }

    @Test
    @DisplayName("Get the Department number from a department name")
    public void getDeptNo2() {
        assertNotEquals("d090", salaryService.getDeptNo("Customer Service"));
    }


    @Test
    @DisplayName("Get the average of a depeartment's salaries")
    public void getDeptSalaryAvg() {
        assertEquals("Development: 59892", salaryService.getDeptSalaryAvg("Development", LocalDate.of(2001,7,3)));
    }
    @Test
    @DisplayName("Get the average of a depeartment's salaries")
    public void getDeptSalaryAvg2() {
        assertNotEquals("Customer Service: 59892", salaryService.getDeptSalaryAvg("Development", LocalDate.of(2001,7,3)));
    }



}