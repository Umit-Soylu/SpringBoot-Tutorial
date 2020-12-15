package com.bilgeadam.tutorial.springboot.dao;

import com.bilgeadam.tutorial.springboot.entities.Employee;

import java.util.Set;

public interface EmployeeDAO {

    Set<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    int delete(int id);
}
