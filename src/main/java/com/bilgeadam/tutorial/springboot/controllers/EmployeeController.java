package com.bilgeadam.tutorial.springboot.controllers;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public String listEmployees(Model model,
                                @RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                @RequestParam(name = "size", defaultValue = defaultSize) Integer size){
        Page<Employee> employeePage = employeeService.findAll(page, size);
        // Add employees
        model.addAttribute("employees", employeePage.getContent());

        //Set pagination array
        model.addAttribute("pageNumbers",
                IntStream.rangeClosed(1, employeePage.getTotalPages()).boxed().collect(Collectors.toList()));
        return "employee/employee-list";
    }

    @GetMapping("/create")
    public String createEmployee(Model model,
                                 @RequestParam(name="id", required = false) Optional<Integer> employeeId){
        model.addAttribute("employee",
                employeeId.isPresent() ? employeeService.findById(employeeId.get()) : new Employee());

        return "employee/employee-create";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute(name = "employee") Employee employee){
        //(Employee) model.getAttribute("employee")
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute(name = "employee") Employee employee){
        employeeService.update(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployeeGet(@RequestParam(name = "id") int employee_id){
        employeeService.delete(employee_id);

        return "redirect:/employees/list";
    }


/*
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findEmployees(@RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                        @RequestParam(name = "size", defaultValue = defaultSize) Integer size){
        return employeeService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeById(@PathVariable(name = "id") int employee_id){
        return employeeService.findById(employee_id);
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
            throw new IllegalRestArgument("Provided id:" + employee.getId() + " does not compatible with " + employee_id);

        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteEmployee(@PathVariable(name = "id") int employee_id){
        employeeService.delete(employee_id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Employee> searchSimilarFirstName(@RequestParam(name = "firstName") String firstName,
                                                 @RequestParam(name = "page", defaultValue = defaultPage) Integer page,
                                                 @RequestParam(name = "size", defaultValue = defaultSize) Integer size,
                                                 @RequestParam(name = "sort") String sort) {
        Sort sortOrder = Sort.by(Sort.Direction.valueOf(sort), "firstName");
        return null; //employeeService.findEmployeesFirstNameLike(firstName, page, size, sortOrder);
    }
    */
}
