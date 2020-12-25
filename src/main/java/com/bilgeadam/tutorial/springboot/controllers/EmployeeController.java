package com.bilgeadam.tutorial.springboot.controllers;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
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

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public String getAllEmployees(Model model,
                                  @RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                  @RequestParam(name = "size", defaultValue = defaultSize) Integer size){
        Page<Employee> employeePage =  employeeService.getEmployees(page, size);
        model.addAttribute("employeePage", employeePage);

        // Set page numbers
        model.addAttribute("pageNumbers", IntStream.rangeClosed(1, employeePage.getTotalPages()).boxed().collect(Collectors.toList()));
        return "employees/employee-list";
    }

    @GetMapping("/save")
    public String createOrModifyEmployee(@RequestParam(name = "id") Optional<Long> id, Model model){
        model.addAttribute("employee", id.isPresent() ? employeeService.findEmployee(id.get()) : new Employee());

        return "employees/employee-create";
    }


    @PostMapping("/save")
    public String createEmployee(@ModelAttribute(name = "employee") Employee employee){
        employeeService.saveEmployee(employee);

        // redirect to list
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(name = "id") Long id, Model model){
        employeeService.deleteEmployee(id);

        // redirect to list
        return "redirect:/employees/list";
    }
/**
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
*/
}
