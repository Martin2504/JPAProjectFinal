package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenderServiceTest {
    private GenderService genderService;
    private SalaryRepository salaryRepository;

    @BeforeEach
    public void setup() {
        salaryRepository = mock(SalaryRepository.class);
        genderService = new GenderService(salaryRepository);
    }

    @Test
    public void testGetGenderPayGapByDepartmentWhenGapExists() {
        String departmentName = "Marketing";
        Double avgMaleSalary = 60000.0;
        Double avgFemaleSalary = 50000.0;
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "F")).thenReturn(avgFemaleSalary);
        String result = genderService.getGenderPayGapByDepartment(departmentName);
        assertEquals("There is a gender pay gap for department " + departmentName + ". Women earn 16.67 % less than men.", result);
    }

    @Test
    public void testGetGenderPayGapByDepartmentWhenGapDoesNotExist() {
        String departmentName = "Engineering";
        Double avgMaleSalary = 70000.0;
        Double avgFemaleSalary = 70000.0;
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "F")).thenReturn(avgFemaleSalary);
        String result = genderService.getGenderPayGapByDepartment(departmentName);
        assertEquals("There is no gender pay gap in department " + departmentName + ".", result);
    }

    @Test
    public void testGetJobTitlePayGapWhenGapExists() {
        String jobTitle = "Manager";
        Double avgMaleSalary = 60000.0;
        Double avgFemaleSalary = 50000.0;
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "F")).thenReturn(avgFemaleSalary);
        String result = genderService.getJobTitlePayGap(jobTitle);
        assertEquals("There is a gender pay gap for job title " + jobTitle + ". Women earn 16.67 % less than men.", result);
    }

    @Test
    public void testGetJobTitlePayGapWhenGapDoesNotExist() {
        String jobTitle = "CEO";
        Double avgMaleSalary = 70000.0;
        Double avgFemaleSalary = 70000.0;
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "F")).thenReturn(avgFemaleSalary);
        String result = genderService.getJobTitlePayGap(jobTitle);
        assertEquals("There is no gender pay gap for job title " + jobTitle + ".", result);
    }

    @Test
    public void testGetCompanyGenderPayGap_WithGap() {
        Double avgMaleSalary = 65000.0;
        Double avgFemaleSalary = 60000.0;
        when(salaryRepository.getAverageSalaryByGender("M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByGender("F")).thenReturn(avgFemaleSalary);

        String result = genderService.getCompanyGenderPayGap();

        assertEquals("There is a gender pay gap of 7.69% in the company.", result);
    }

    @Test
    public void testGetCompanyGenderPayGap_WithoutGap() {
        Double avgMaleSalary = 70000.0;
        Double avgFemaleSalary = 70000.0;
        when(salaryRepository.getAverageSalaryByGender("M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByGender("F")).thenReturn(avgFemaleSalary);

        String result = genderService.getCompanyGenderPayGap();

        assertEquals("There is no gender pay gap in the company.", result);
    }

    @ParameterizedTest
    @CsvSource({
            "Development, 55000.0, 50000.0, In department Development; there is a gender pay gap. Women earn 9.09% less than men.",
            "Finance, 60000.0, 60000.0, In department Finance; there is no gender pay gap.",
            "Marketing, 50000.0, 55000.0, In department Marketing; women earn 9.09% more than men."
    })
    public void testGetDepartmentPayGap(String departmentName, Double avgMaleSalary, Double avgFemaleSalary, String expected) {
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "F")).thenReturn(avgFemaleSalary);

        String result = genderService.getGenderPayGapByDepartment(departmentName);
        expected =  expected.replace(";", ",");
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"Senior Engineer, 80000.0, 60000.0, There is a gender pay gap for job title Senior Engineer. Women earn 25.0% less than men.",
            "Staff, 50000.0, 50000.0, There is no gender pay gap for job title Staff.",
            "Senior Staff, 75000.0, 70000.0, There is a gender pay gap for job title Senior Staff. Women earn 6.67% less than men.",
            "Assistant Engineer, 45000.0, 40000.0, There is a gender pay gap for job title Assistant Engineer. Women earn 12.5% less than men.",
            "Technique Leader, 90000.0, 80000.0, There is a gender pay gap for job title Technique Leader. Women earn 11.11% less than men."})
    void testGetJobTitlePayGap(String jobTitle, Double avgMaleSalary, Double avgFemaleSalary, String expected) {
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "F")).thenReturn(avgFemaleSalary);
        String result = genderService.getJobTitlePayGap(jobTitle);
        assertEquals(expected, result);
    }

    public void testGetCompanyGenderPayGap(Double avgMaleSalary, Double avgFemaleSalary, Double expectedGap) {
        when(salaryRepository.getAverageSalaryByGender("M")).thenReturn(avgMaleSalary);
        when(salaryRepository.getAverageSalaryByGender("F")).thenReturn(avgFemaleSalary);

        String result = genderService.getCompanyGenderPayGap();

        if (expectedGap == null) {
            assertNull(result);
        } else if (expectedGap == 0) {
            assertEquals("There is no gender pay gap in the company.", result);
        } else {
            assertEquals("There is a gender pay gap of " + expectedGap + "% in the company.", result);
        }
    }
}