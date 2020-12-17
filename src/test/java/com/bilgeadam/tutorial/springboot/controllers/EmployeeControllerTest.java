package com.bilgeadam.tutorial.springboot.controllers;


import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class) // Loads only given controller, nothing else.
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee testEmployee;

    @Test
    void findEmployees() throws Exception {
        // Setup service answer for test purposes.
        testEmployee = new Employee("Test", "Java");
        given(employeeService.findAll()).willReturn(Collections.singletonList(testEmployee));

        // Prepare test object.
        mockMvc.perform(get("/employees")).
                andDo(print()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].firstName").value(testEmployee.getFirstName()));
    }

    @Test
    void addEmployee() throws Exception {
        //mock(employeeService.save(any(Employee.class)));

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).
                content("{ \"firstName\": \"Spring\", \"lastName\": \"Test\"}")).
                andDo(print()).
                andExpect(status().isCreated());

        Mockito.verify(employeeService).save(any(Employee.class));
    }

    @Test
    void updateEmployee() throws Exception {
        // Setup service answer for test purposes.
        testEmployee = new Employee("Test", "Java");
        testEmployee.setId(45);

        mockMvc.perform(put("/employees/" + testEmployee.getId()).contentType(MediaType.APPLICATION_JSON).
                content("{ \"id\":" + testEmployee.getId() + ", \"firstName\": \"Spring\", \"lastName\": \"Test\"}")).
                andDo(print()).
                andExpect(status().isNoContent());
        Mockito.verify(employeeService).update(any(Employee.class));
    }

    @Test
    void deleteEmployee() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/employees/" + id).contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isGone());

        Mockito.verify(employeeService).delete(id);
    }
}