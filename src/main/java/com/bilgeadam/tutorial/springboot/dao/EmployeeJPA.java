package com.bilgeadam.tutorial.springboot.dao;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPA extends JpaRepository<Employee, Integer> {

}
