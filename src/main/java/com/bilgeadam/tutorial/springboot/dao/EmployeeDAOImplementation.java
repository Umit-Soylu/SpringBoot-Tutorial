package com.bilgeadam.tutorial.springboot.dao;

import com.bilgeadam.tutorial.springboot.entities.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityManager;
import java.util.Set;

public class EmployeeDAOImplementation implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Employee> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> employeeQuery = currentSession.createQuery("FROM Employees", Employee.class);

        return (Set<Employee>) employeeQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public int delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query employeeQuery = currentSession.createQuery("DELETE FROM Employees WHERE id := emp_id");
        employeeQuery.setParameter("emp_id", id);

        return employeeQuery.executeUpdate();
    }
}
