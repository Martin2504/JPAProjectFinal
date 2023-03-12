package com.sparta.mg.jpaproject.controllers.departmentcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.mg.jpaproject.model.entities.Department;
import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
import com.sparta.mg.jpaproject.services.ApiKeyService;
import com.sparta.mg.jpaproject.tools.CRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ApiKeyService apiKeyService;

    @Mock
    private ObjectMapper objectMapper;

    @Autowired
    private ObjectMapper mapper;

    @InjectMocks
    @Spy
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    @DisplayName("Creting a new department")
    public void testCreateNewDepartment() throws Exception {
        Department department = mock(Department.class);
        when(apiKeyService.validateUser(anyString(), eq(CRUD.CREATE))).thenReturn(true);
        when(departmentRepository.getDepartmentByName("d005")).thenReturn(Optional.of(department));

        mockMvc.perform(MockMvcRequestBuilders.post("/department")
                .param("departmentName", "mock department name")
                .header("x-api-key", "c47b64b4-1822-4732-aa46-63fae45895bd"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"message\":\"The department of name mock department name has been saved\"}"));
    }

    @Test
    @DisplayName("Get all Departments")
    public void testGetAllDepartments() throws Exception {
        Department department1 = new Department();
        department1.setId("d001");
        department1.setDeptName("department 1");
        Department department2 = new Department();
        department2.setId("d002");
        department2.setDeptName("department 2");

        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);

        when(apiKeyService.validateUser(anyString(), eq(CRUD.READ))).thenReturn(true);
        when(departmentRepository.findAll()).thenReturn(departments);
        when(objectMapper.writeValueAsString(any())).thenReturn(mapper.writeValueAsString(departments));

        mockMvc.perform((get("/departments"))
                .header("x-api-key", "c47b64b4-1822-4732-aa46-63fae45895bd"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":\"d001\",\"deptName\":\"department 1\"},{\"id\":\"d002\",\"deptName\":\"department 2\"}]"))
                .andExpect(jsonPath("$[0].id").value("d001"));
    }

    @Test
    @DisplayName("Update the department")
    void testUpdateDepartment() throws Exception {
        Department department = mock(Department.class);

        Department example = new Department();
        example.setId("d001");
        example.setDeptName("mock department");

        when(apiKeyService.validateUser(anyString(), eq(CRUD.UPDATE))).thenReturn(true);
        when(departmentRepository.findById("d001")).thenReturn(Optional.of(department));
        when(objectMapper.writeValueAsString(anyString())).thenReturn(mapper.writeValueAsString(example));

        mockMvc.perform(MockMvcRequestBuilders.patch("/department/d001")
                .param("deptName", "mock department name")
                .header("x-api-key", "c47b64b4-1822-4732-aa46-63fae45895bd"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete a department")
    public void testDeletingDepartment() throws Exception {
        Department department = mock(Department.class);

        when(apiKeyService.validateUser(anyString(), eq(CRUD.DELETE))).thenReturn(true);
        when(departmentRepository.findById(anyString())).thenReturn(Optional.ofNullable(department));
        when(objectMapper.writeValueAsString(anyString())).thenReturn(mapper.writeValueAsString("Random"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/department/d001")
                        .header("x-api-key", "c47b64b4-1822-4732-aa46-63fae45895bd"))
                .andExpect(status().isOk());

    }



}