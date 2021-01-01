package com.bilgeadam.tutorial.springboot.dao;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPA extends JpaRepository<Employee, Integer> {

    Page<Employee> findByFirstNameLike(String firstName, Pageable pageable);

}
