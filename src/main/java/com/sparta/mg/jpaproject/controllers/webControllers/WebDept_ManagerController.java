package com.sparta.mg.jpaproject.controllers.webControllers;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
    public String deptManagerSearch(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/department_to_search";
    }

    //Create
//    @GetMapping("/createDeptManager")
//    public String createDeptManager(Model model) {
//        model.addAttribute("allDepartments", departmentRepository.findAll());
//        model.addAttribute("deptManager", new DeptManager());
//        return "dept_manager_files/createDeptManager";
//    }
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

    @GetMapping("/updateDeptManager")
    public String updateDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/updateDeptManager";
    }

    //Delete
    @GetMapping("/delete")
    public String deleteDeptManager(Model model) {
        model.addAttribute("allDepartments", departmentRepository.findAll());
        return "dept_manager_files/deleteDeptManager";
    }

}
