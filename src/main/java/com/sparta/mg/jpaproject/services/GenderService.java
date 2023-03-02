package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    private SalaryRepository salaryRepository;

    @Autowired
    public GenderService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public String calculatePayGap(Double avgMaleSalary, Double avgFemaleSalary) {
        // Check if both averages are present
        if (avgMaleSalary == null || avgFemaleSalary == null) {
            return "Unable to determine if there is a gender pay gap";
        }

        // Calculate the pay gap as a percentage
        double payGap = ((avgMaleSalary - avgFemaleSalary) / avgMaleSalary) * 100.0;
        payGap = Math.round(payGap * 100.0) / 100.0;
        // Check if the pay gap is greater than 0%
        if (payGap > 0.0) {
            return "there is a gender pay gap. Women earn " + payGap + "% less than men.";
        } else {
            return "there is no gender pay gap.";
        }
    }

    public String getGenderPayGapByDepartment(String departmentName) {
        Double avgMaleSalary = salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "M");
        Double avgFemaleSalary = salaryRepository.getAverageSalaryByDepartmentAndGender(departmentName, "F");

        String result = calculatePayGap(avgMaleSalary, avgFemaleSalary);
        return "In department " + departmentName + ", " + result;
    }

    public String getJobTitlePayGap(String jobTitle) {
        Double avgMaleSalary = salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "M");
        Double avgFemaleSalary = salaryRepository.getAverageSalaryByJobTitleAndGender(jobTitle, "F");

        String result = calculatePayGap(avgMaleSalary, avgFemaleSalary);
        return "For job title " + jobTitle + ", " + result;
    }

    public String getCompanyGenderPayGap() {
        Double maleSalary = salaryRepository.getAverageSalaryByGender("M");
        Double femaleSalary = salaryRepository.getAverageSalaryByGender("F");

        String result = calculatePayGap(maleSalary, femaleSalary);
        return "In the company as a whole, " + result;
    }
}

