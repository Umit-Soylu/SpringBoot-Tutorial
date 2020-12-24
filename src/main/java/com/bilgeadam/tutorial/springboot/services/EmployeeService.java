package com.bilgeadam.tutorial.springboot.services;


import com.bilgeadam.tutorial.springboot.dao.EmployeeJPA;
import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {
    private final EmployeeJPA employeeJPA;

    @Autowired
    public EmployeeService(EmployeeJPA employeeJPA) {
        this.employeeJPA = employeeJPA;
    }

    @Transactional(readOnly = true, timeout = 100)
    public Page<Employee> getEmployees(int page, int size){
        Page<Employee> employeePage = employeeJPA.findAll(PageRequest.of(page, size));

        if (employeePage.isEmpty())
            throw new EntityNotFound(Employee.class);
        else
            return employeePage;
    }

    @Transactional(readOnly = true, timeout = 100)
    public Employee findEmployee(Long id){
       return employeeJPA.findById(id).
               orElseThrow(() -> new EntityNotFound(Employee.class, id));
    }

    @Transactional(timeout = 100)
    public Employee saveEmployee(Employee employee){
        return employeeJPA.save(employee);
    }

    @Transactional(timeout = 100)
    public void deleteEmployee(Long id) {
        employeeJPA.deleteById(id);
    }

    @Transactional(timeout = 100, readOnly = true)
    public Page<Employee> findEmployeesByAge(Integer age, Integer page, Integer size){
        return employeeJPA.findByAge(age, PageRequest.of(page, size));
    }

    @Transactional(timeout = 100, readOnly = true)
    public Page<Employee> findEmployeesFirstNameLike(String firstName, Integer page, Integer size, Sort sort){
        return employeeJPA.findByFirstNameLike(firstName, PageRequest.of(page, size, sort));
    }

    public void test(){
        Example.of(new Employee());
        ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues();
    }
}