package com.bilgeadam.tutorial.springboot.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private long id;

    @Column(name = "department", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees;

    public Department() {
    }

    /**
     * Department with essentials.
     * @param name name of the department
     */
    public Department(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "employees=" + employees +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
