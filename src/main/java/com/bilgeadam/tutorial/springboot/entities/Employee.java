package com.bilgeadam.tutorial.springboot.entities;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_role")
    private Roles currentRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER) //Fetches the values always.
    private Set<Address> addresses;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "employees_departments",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "emp_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "no", name = "dept_id")})
    private Set<Department> departments;

    // Hibernate needs this
    public Employee() {
    }

    /**
     * Constructor with essentials
     * @param firstName First name
     * @param lastName  Last name
     */
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Roles getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Roles currentRole) {
        this.currentRole = currentRole;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday + '\'' +
                ", salary=" + salary + '\'' +
                ", currentRole=" + currentRole + '\'' +
                ", addresses=" + addresses +
                ", departments=" + departments +
                '}';
    }
}
