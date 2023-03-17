package com.sparta.mg.jpaproject.controllers.webControllers;

import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import com.sparta.mg.jpaproject.model.repositories.TitleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"employeeToCreateExtra", "deptId",
        "titleName", "titleInput", "salaryInput", "titleValue"})
public class WebEmployeesController {
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final TitleRepository titleRepository;

    public WebEmployeesController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, TitleRepository titleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.titleRepository = titleRepository;
    }

    @ModelAttribute("employeeToCreateExtra")
    public Employee employeeToCreateExtra() {
        return new Employee();
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @GetMapping("/employee/create")
    public String getCreateEmployeeForm(Model model) {
        model.addAttribute("employeeToCreate", new Employee());
        return "EmployeePages/create-employee-form";
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @GetMapping("/employee/createExtra")
    public String getCreateExtraEmployeeForm(Model model) {
        model.addAttribute("employeeToCreate", new Employee());
        model.addAttribute("allDepartments", departmentRepository.findAll());
        List<String> allTitles = titleRepository.getAllTitles();
        allTitles.add("Other");
        model.addAttribute("allTitles",allTitles);
        return "EmployeePages/create-employee-extra-form";
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @PostMapping("/employee/save")
    public String save(@ModelAttribute("employeeToCreate") Employee employeeToCreate,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        employeeRepository.save(employeeToCreate);
        redirectAttributes.addFlashAttribute("status", "Employee successfully created");
        return "redirect:/employee?empNo=" + employeeToCreate.getId();
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @PostMapping("/employee/saveExtra")
    public String saveExtra(@ModelAttribute("employeeToCreateExtra") Employee employeeToCreate,
                       @ModelAttribute("deptId") String deptId,
                            @ModelAttribute("titleName") String titleName,
                            @ModelAttribute("titleInput") String titleInput,
                            @ModelAttribute("salaryInput") Integer salaryInput,
                            Model model,
                       RedirectAttributes redirectAttributes) {
        System.out.println(employeeToCreate);
        System.out.println(deptId);
        System.out.println(titleName);
        System.out.println(titleInput);
        System.out.println(salaryInput);
        if (titleName == "Other") {
            model.addAttribute("titleValue", titleInput);
        } else {
            model.addAttribute("titleValue", titleName);
        }

        model.addAttribute("deptName", departmentRepository.findById(deptId).get().getDeptName());
//        employeeRepository.save(employeeToCreate);
//        redirectAttributes.addFlashAttribute("status", "Employee successfully created");
        return "/EmployeePages/create-employee-extra-confirmation";
    }

    @PreAuthorize("hasRole('ROLE_BASIC')")
    @GetMapping("/employee/search")
    public String getEmployeeSearchPage() {
        return "EmployeePages/search-employee-page";
    }

    @PreAuthorize("hasRole('ROLE_BASIC')")
    @GetMapping("/employee")
    public String getEmployee(Model model, @RequestParam Integer empNo) {
        Optional<Employee> emp1 = employeeRepository.findById(empNo);
        if (emp1.isPresent()) {
            model.addAttribute("employee", emp1.get());
        }
        return "EmployeePages/employee";
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @GetMapping("/employee/edit")
    public String getEditSalary(@RequestParam("emp_No") Integer emp_No, Model model){
        Employee emp = employeeRepository.findById(emp_No).orElse(null);
        model.addAttribute("empToEdit", emp);
        return "EmployeePages/employee-edit-form";
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    @PostMapping("/update/employee")
    public String updateSalary(Employee empToEdit, RedirectAttributes redirectAttributes){
        employeeRepository.saveAndFlush(empToEdit);
        redirectAttributes.addFlashAttribute("status", "Employee successfully updated");
        return "redirect:/employee?empNo=" + empToEdit.getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/employee/delete")
    public String deleteSalary(
            @RequestParam("emp_No") Integer emp_No,
            RedirectAttributes redirectAttributes) {
        employeeRepository.deleteById(emp_No);
        redirectAttributes.addFlashAttribute("status", "Employee successfully deleted");
        return "redirect:/employees";
    }

}
