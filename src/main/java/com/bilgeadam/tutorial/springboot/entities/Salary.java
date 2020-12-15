package com.bilgeadam.tutorial.springboot.entities;

import javax.persistence.*;

@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type", unique = true)
    private SalaryTypes type;

    @OneToOne(mappedBy = "salary")
    private Employee employee;


    // No arg constructor for hibernate
    public Salary() {
    }

    /**
     * Constructor with salary value
     * @param salary the salary value
     */
    public Salary(int salary) throws Exception {
        setSalary(salary);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) throws Exception {
        if (salary < 0)
            throw new Exception("Salary cannot be negative");

        this.salary = salary;
    }

    public SalaryTypes getType() {
        return type;
    }

    public void setType(SalaryTypes type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", salary=" + salary +
                ", type=" + type +
                '}';
    }
}
