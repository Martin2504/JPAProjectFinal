package com.sparta.mg.jpaproject.controllers.webControllers;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Salary;
import com.sparta.mg.jpaproject.model.entities.SalaryId;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import com.sparta.mg.jpaproject.model.repositories.SalaryRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class WebSalariesController {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    public WebSalariesController(SalaryRepository salaryRepository,
                                 EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/salary/create")
    public String createSalaryForm(@RequestParam("empNo") Integer empNo, Model model) {
        Salary salary = new Salary();
        salary.setEmpNo(employeeRepository.findById(empNo).orElse(null));
        model.addAttribute("salaryToCreate", salary);
        return "SalariesPages/create-salary-form";
    }

    @PostMapping("/salary/save")
    public String save(@ModelAttribute("salaryToCreate") Salary salaryToCreate,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        salaryRepository.save(salaryToCreate);
        Optional<Employee> employee = employeeRepository.findById(salaryToCreate.getEmpNo().getId());
        model.addAttribute("employee", employee);
//        model.addAttribute("salaries", employee.getSalaries());
        redirectAttributes.addFlashAttribute("status", "Salary successfully created");
        return "redirect:/employee/salaries?empNo=" + employee.get().getId();
    }


    @GetMapping("/salary/search")
    public String getSalarySearchPage() {
        return "SalariesPages/searchSalariesPage";
    }

    @GetMapping("/employee/salaries")
    public String getSalariesByEmpNo(Model model,
                                     @RequestParam Integer empNo) {
        Optional<Employee> emp1 = employeeRepository.findById(empNo);
        if (emp1.isPresent()) {
            model.addAttribute("salaries", salaryRepository.findSalariesByEmpNo(emp1.get()));
            model.addAttribute("employeeNo", emp1.get().getId());
        }
        return "SalariesPages/salaries";
    }

    @PostMapping("/salary/delete")
    public String deleteSalary(
            @RequestParam("emp_No") Integer emp_No,
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            RedirectAttributes redirectAttributes) {
        SalaryId salaryId = new SalaryId();
        salaryId.setEmpNo(emp_No);
        salaryId.setFromDate(fromDate);
        salaryRepository.deleteById(salaryId);
        redirectAttributes.addFlashAttribute("status", "Salary successfully deleted");
        return "redirect:/employee/salaries?empNo=" + emp_No;
    }

    @GetMapping("/salary/edit")
    public String getEditSalary(@RequestParam("emp_No") Integer emp_No,
                                @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, Model model){
        SalaryId salaryId = new SalaryId();
        salaryId.setEmpNo(emp_No);
        salaryId.setFromDate(fromDate);
        Salary salary = salaryRepository.findById(salaryId).orElse(null);
        model.addAttribute("salaryToEdit", salary);
        return "SalariesPages/salary-edit-form";
    }

    @PostMapping("/update/salary")
        public String updateSalary(Salary salaryToEdit, RedirectAttributes redirectAttributes){
        salaryRepository.saveAndFlush(salaryToEdit);
        redirectAttributes.addFlashAttribute("status", "Salary successfully updated");
        return "redirect:/employee/salaries?empNo=" + salaryToEdit.getId().getEmpNo();
    }

}
