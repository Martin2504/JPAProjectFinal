package com.sparta.mg.jpaproject.controllers.webControllers;

import com.sparta.mg.jpaproject.model.entities.DeptManager;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dept-manager")
public class WebDept_ManagerController {
    // Samir

    private final DeptManagerRepository deptManagerRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public WebDept_ManagerController(DeptManagerRepository deptManagerRepository, DepartmentRepository departmentRepository) {
        this.deptManagerRepository = deptManagerRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping()
    public String deptManagerHomepage() {
        return "dept_manager_files/department_to_search";
    }

    //Create
    @GetMapping("/createDeptManager")
    public String createDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        model.addAttribute("deptManager", new DeptManager());
        return "dept_manager_files/createDeptManager";
    }
    //Read

    @GetMapping("/getDeptManager")
    public String getDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/getDeptManager";
    }

    //Update

    @GetMapping("/updateDeptManager")
    public String updateDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/updateDeptManager";
    }

    //Delete
    @GetMapping("/deleteDeptManager")
    public String deleteDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/deleteDeptManager";
    }

}
