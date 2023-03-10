package com.sparta.mg.jpaproject.controllers.employeecontrollers;

import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.entities.DeptEmp;
import com.sparta.mg.jpaproject.model.entities.DeptEmpId;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class EmployeeDepartmentController {
    package com.sparta.mg.jpaproject.controllers.employeecontrollers;

import com.sparta.mg.jpaproject.model.entities.*;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

    @RestController
    public class EmployeeDepartmentController {

        private DeptEmpRepository deptEmpRepository;
        private EmployeeRepository employeeRepository;
        private Integer empNo;

        @Autowired
        public EmployeeDepartmentController(DeptEmpRepository deptEmpRepository, EmployeeRepository employeeRepository ){
            this.deptEmpRepository =deptEmpRepository;
            this.employeeRepository = employeeRepository;
        }

        @PostMapping("/employees/deptemp/{empId}")
        public ResponseEntity<String> setEmployeeDept(
                @PathVariable Integer empId,
                @RequestParam("deptNo") Department deptNo,
                @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {

            Optional<Employee> emp1 = employeeRepository.findById(empId);
            if (emp1.isPresent()) {
                var deptEmp = new DeptEmp();
                deptEmp.setDeptNo(deptNo);
                deptEmp.setToDate(toDate);
                deptEmp.setFromDate(fromDate);
                var deptEmpId = new DeptEmpId();
                deptEmpId.setDeptNo(deptNo.getDeptName());
                return ResponseEntity.ok("Department updated for Employee " + empId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        }

        @GetMapping("/employees/deptemp/{deptEmpId}")
        public ResponseEntity<String> getDeptByEmpNo(@PathVariable Integer empNo) {
            Optional<Employee> emp1 = employeeRepository.findById(empNo);
            if (emp1.isPresent()) {
                DeptEmp deptEmp = new DeptEmp();
                return ResponseEntity.ok(deptEmp.getDeptNo().toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        }

        @PatchMapping("/employees/deptemp/{empId}")
        public ResponseEntity<String> updateEmployeeDept(
                @PathVariable Integer empId,
                @RequestParam("deptNo") Department deptNo,
                @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {

            return setEmployeeDept(empNo,deptNo,fromDate,toDate);
        }



    }

}
