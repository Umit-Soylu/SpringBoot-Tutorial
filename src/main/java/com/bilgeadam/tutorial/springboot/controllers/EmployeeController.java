package com.bilgeadam.tutorial.springboot.controllers;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    // Default parameters for pagination
    private static final String defaultPage = "0", defaultSize = "5";
    private static final Sort.Direction defaultSorting = Sort.Direction.ASC;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> getAllEmployees(@RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                          @RequestParam(name = "size", defaultValue = defaultSize) Integer size){
        return employeeService.getEmployees(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Employee findEmployee(@PathVariable(name = "id") Long id){
        return employeeService.findEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/age/{value}")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Employee> findByAge(@PathVariable(name = "value") Integer age,
                                    @RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                    @RequestParam(name = "size", defaultValue = defaultSize) Integer size){
        return employeeService.findEmployeesByAge(age, page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Employee> searchSimilarFirstName(@RequestParam(name = "firstName") String firstName,
                                                 @RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                                 @RequestParam(name = "size", defaultValue = defaultSize) Integer size,
                                                 @RequestParam(name = "sort") String sort){
        Sort sortOrder = Sort.by(Sort.Direction.valueOf(sort), "firstName");

        return employeeService.findEmployeesFirstNameLike(firstName, page, size, sortOrder);
    }

}
