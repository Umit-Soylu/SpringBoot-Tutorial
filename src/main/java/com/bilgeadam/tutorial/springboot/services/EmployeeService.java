package com.bilgeadam.tutorial.springboot.services;

import com.bilgeadam.tutorial.springboot.dao.EmployeeDAO;
import com.bilgeadam.tutorial.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(int id){
        Employee employee = employeeDAO.findById(id);

        if (employee == null)
            throw new RuntimeException("Requested resource not found");
        else
            return employee;
    }

    @Transactional
    public void save(Employee employee) {
        if (employeeDAO.findById(employee.getId()) != null)
            throw new RuntimeException("Given employee already exists");
        else
            employeeDAO.save(employee);
    }

    @Transactional
    public void update(Employee employee){
        if (employeeDAO.findById(employee.getId()) == null)
            throw new RuntimeException("Requested employee does not exist");
        else
            employeeDAO.save(employee);
    }

    @Transactional
    public void delete(int employee_id){
        if (employeeDAO.findById(employee_id) == null)
            throw new RuntimeException("Requested employee does not exist");
        else
            employeeDAO.delete(employee_id);
    }
}
