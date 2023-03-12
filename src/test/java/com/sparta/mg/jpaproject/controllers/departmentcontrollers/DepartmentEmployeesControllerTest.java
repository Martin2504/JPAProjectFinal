package com.sparta.mg.jpaproject.controllers.departmentcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.entities.Employee;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.model.repositories.DeptEmpRepository;
import com.sparta.mg.jpaproject.services.ApiKeyService;
import com.sparta.mg.jpaproject.tools.CRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentEmployeesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private ApiKeyService apiKeyService;

    @Mock
    private DeptEmpRepository deptEmpRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Autowired
    private ObjectMapper mapper;

    @InjectMocks
    private DepartmentEmployeesController departmentEmployeesController;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentEmployeesController).build();
    }

    @Test
    @DisplayName("Get all employees of a department")
    public void testGetAllEmployeesOfADepartment() throws Exception {
        Department department = mock(Department.class);

        Pageable pageable = PageRequest.of(0, 5);

        Employee employee = new Employee();
        Employee employee1 = new Employee();


        List<Employee> users = new ArrayList<>();
        users.add(employee);
        users.add(employee1);

        Page<Employee> pages = new PageImpl<Employee>(users, pageable, users.size());

        when(apiKeyService.validateUser(anyString(), eq(CRUD.READ))).thenReturn(true);
        when(departmentRepository.findById("d001")).thenReturn(Optional.ofNullable(department));
        when(deptEmpRepository.getEmployeesByDeptNo("d001", pageable)).thenReturn(pages);
        when(objectMapper.writeValueAsString(any())).thenReturn(mapper.writeValueAsString("random"));

        mockMvc.perform((get("/department/d001/employees/0/5"))
                        .header("x-api-key", "c47b64b4-1822-4732-aa46-63fae45895bd"))
                .andExpect(status().isOk());

    }
}