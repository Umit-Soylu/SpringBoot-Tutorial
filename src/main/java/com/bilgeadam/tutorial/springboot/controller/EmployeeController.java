package com.bilgeadam.tutorial.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String findEmployees(){
        return "My First Page with requested time: " + LocalDateTime.now();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployee(@RequestBody Integer employee){
        return "A new employee will be added. Body param is: " + employee ;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateEmployee(@PathVariable(name = "id") int employee_id){
        return "Update request acquired for: " + employee_id;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public String deleteEmployee(@PathVariable(name = "id") int employee_id){
        return "Delete request acquired for: " + employee_id;
    }

}
