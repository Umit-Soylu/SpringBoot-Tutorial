package com.bilgeadam.tutorial.springboot.controllers;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findEmployees(){
        return employeeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable(name = "id") int employee_id, @Valid @RequestBody Employee employee){
        if (employee.getId() != employee_id)
            throw new RuntimeException("Illegal call");

        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteEmployee(@PathVariable(name = "id") int employee_id){
        employeeService.delete(employee_id);
    }

}
