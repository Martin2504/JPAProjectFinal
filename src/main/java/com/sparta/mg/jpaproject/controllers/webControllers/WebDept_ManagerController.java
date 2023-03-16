package com.sparta.mg.jpaproject.controllers.webControllers;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.entities.DeptManager;
import com.sparta.mg.jpaproject.model.entities.DeptManagerId;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptManagerRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/dept-manager")
public class WebDept_ManagerController {
    // Samir

    private final DeptManagerRepository deptManagerRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public WebDept_ManagerController(DeptManagerRepository deptManagerRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.deptManagerRepository = deptManagerRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public String deptManagerSearch(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/department_to_search";
    }

    //Create
    @GetMapping("/createForm")
    public String createDeptManagerForm(Model model, String deptId) {
        DeptManager deptManager = new DeptManager();
        deptManager.setDeptNo(departmentRepository.findById(deptId).get());
        DeptManagerId deptManagerId = new DeptManagerId();
        deptManagerId.setDeptNo(deptId);
        deptManager.setId(deptManagerId);
        model.addAttribute("deptManagerToCreate", deptManager);
        return "dept_manager_files/department-manager-create-form";
    }

    @PostMapping("/create")
    public String saveDeptManager(@ModelAttribute("deptManagerToCreate") DeptManager deptManager, Model model,
                                  RedirectAttributes redirectAttributes) {
        deptManager.setEmpNo(employeeRepository.findById(deptManager.getId().getEmpNo()).get());
        System.out.println(deptManager);
        System.out.println(deptManager.getDeptNo());
        System.out.println(deptManager.getEmpNo());
        System.out.println(deptManager.getId());
        deptManager.setDeptNo(departmentRepository.findById(deptManager.getId().getDeptNo()).get());
        deptManagerRepository.saveAndFlush(deptManager);
        redirectAttributes.addFlashAttribute("status", "Dept Manager successfully created");
        return "redirect:/dept-manager/getDeptManagers?deptId=" + deptManager.getId().getDeptNo();
    }
    //Read

    @GetMapping("/getDeptManagers")
    public String getDepartmentManagersByDeptId(Model model,
                                     @RequestParam String deptId) {
        Optional<Department> dept1 = departmentRepository.findById(deptId);
        if (dept1.isPresent()) {
            model.addAttribute("deptManagers", deptManagerRepository.getDeptManagersById_DeptNo(deptId));
            model.addAttribute("deptId", dept1.get().getId());
        }
        return "dept_manager_files/departmentManagers";
    }

    //Update

    @GetMapping("/editDeptManager")
    public String updateDeptManager(Model model, @RequestParam String deptId, @RequestParam Integer emp_No) {
        DeptManagerId deptManagerId = new DeptManagerId();
        deptManagerId.setEmpNo(emp_No);
        deptManagerId.setDeptNo(deptId);
        DeptManager deptManager = deptManagerRepository.findById(deptManagerId).orElse(null);
        model.addAttribute("deptManagerToEdit", deptManager);
        return "dept_manager_files/department-manager-edit-form";
    }

    @PostMapping("/update")
    public String updateSalary(@ModelAttribute("deptManagerToEdit") DeptManager deptManager, RedirectAttributes redirectAttributes){
        deptManagerRepository.saveAndFlush(deptManager);
        redirectAttributes.addFlashAttribute("status", "Salary successfully updated");
        return "redirect:/dept-manager/getDeptManagers?deptId=" + deptManager.getId().getDeptNo();
    }

    //Delete
    @PostMapping("/delete")
    public String deleteDeptManager(Model model, @RequestParam String deptId, @RequestParam Integer emp_No,
            RedirectAttributes redirectAttributes) {
        DeptManagerId deptManagerId = new DeptManagerId();
        deptManagerId.setEmpNo(emp_No);
        deptManagerId.setDeptNo(deptId);
        deptManagerRepository.deleteById(deptManagerId);
        redirectAttributes.addFlashAttribute("status", "Salary successfully deleted");
        return "redirect:/dept-manager/getDeptManagers?deptId=" + deptManagerId.getDeptNo();
    }

}
