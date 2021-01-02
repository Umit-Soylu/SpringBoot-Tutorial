package com.bilgeadam.tutorial.springboot.services;

import com.bilgeadam.tutorial.springboot.dao.EmployeeJPA;
import com.bilgeadam.tutorial.springboot.entities.Employee;
import com.bilgeadam.tutorial.springboot.utils.IllegalRestArgument;
import com.bilgeadam.tutorial.springboot.utils.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeJPA employeeDAO;

    @Autowired
    public EmployeeService(EmployeeJPA employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAll(int page, int size){
        Page<Employee> employeeList = employeeDAO.findAll(PageRequest.of(page, size));

        if(employeeList.isEmpty())
            throw new RecordNotFound(Employee.class);
        else
            return employeeList;
    }

    @Transactional(readOnly = true)
    public Employee findById(int id){
        return employeeDAO.findById(id).
                orElseThrow(() -> new RecordNotFound(Employee.class));
    }

    @Transactional
    public void save(Employee employee) {
        if (employee.getId() != null && employeeDAO.findById(employee.getId()).isPresent())
            throw new IllegalRestArgument("Given employee already exists");
        else
            employeeDAO.save(employee);
    }

    @Transactional
    public void update(Employee employee){
        if (employeeDAO.findById(employee.getId()).isEmpty())
            throw new IllegalRestArgument("Requested employee does not exist");
        else
            employeeDAO.save(employee);
    }

    @Transactional
    public void delete(int employee_id){
        if (employeeDAO.findById(employee_id).isEmpty())
            throw new IllegalRestArgument("Requested employee does not exist");
        else
            employeeDAO.deleteById(employee_id);
    }
}
