package com.sparta.mg.jpaproject.controllers.employeecontrollers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.entities.Title;
import com.sparta.mg.jpaproject.model.entities.TitleId;
import com.sparta.mg.jpaproject.model.repositories.EmployeeRepository;
import com.sparta.mg.jpaproject.model.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeTitleController {
    //humza

    private final TitleRepository titleRepository;
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper mapper;

    @Autowired
    public EmployeeTitleController(TitleRepository titleRepository, ObjectMapper mapper, EmployeeRepository employeeRepository) {
        this.titleRepository = titleRepository;
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/employee/title/{id}")
    public ResponseEntity<String> getListOfTitlesById(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {

            List<Title> titles = titleRepository.findTitleByEmpNo(optionalEmployee.get());
            return ResponseEntity.ok(titles.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

    @PatchMapping(value = "/employee/{id}/{title}/{fromDate}")
    public ResponseEntity<String> updateTitleById(
            @PathVariable("id") Integer id,
            @PathVariable("title") String title,
            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {

        Optional<Title> existingTitle = titleRepository.findTitleByEmpFromDateTitle(id, fromDate, title);

        if (existingTitle.isPresent()) {
            existingTitle.get().setToDate(toDate);
            titleRepository.save(existingTitle.get());
            return ResponseEntity.ok("ToDate Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ToDate not found");
        }
    }

    @PostMapping(value = "/employee/{id}/title")
    public ResponseEntity<String> createNewTitle(
            @PathVariable("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {

        Optional<Employee> emp1 = employeeRepository.findById(id);
        if (emp1.isPresent()) {
            Title title1 = new Title();
            title1.setToDate(toDate);
            TitleId titleId = new TitleId();
            titleId.setTitle(title);
            titleId.setEmpNo(id);
            titleId.setFromDate(fromDate);
            title1.setId(titleId);
            title1.setEmpNo(emp1.get());
            System.out.println(title1);
            titleRepository.save(title1);
            return ResponseEntity.ok("New Title created");
        } else {
            return new ResponseEntity<>(
                    "Employee With " + id + " does not exist",
                    new HttpHeaders(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/employee/{id}/title")
    public ResponseEntity<String> deleteTitle(
            @PathVariable("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {

        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            Title title1 = new Title();
            TitleId titleId = new TitleId();
            title1.setToDate(toDate);
            titleId.setTitle(title);
            titleId.setEmpNo(id);
            titleId.setFromDate(fromDate);
            title1.setId(titleId);
            title1.setEmpNo(emp.get());
            titleRepository.delete(title1);
            return ResponseEntity.ok("Title Deleted");
        } else {
            return new ResponseEntity<>(
                    "Employee with ID " + id + " not found",
                    new HttpHeaders(),
                    HttpStatus.NOT_FOUND);
        }
    }
}
