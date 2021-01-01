package com.bilgeadam.tutorial.springboot.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
    private static final int maxLength = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "first_name", length = maxLength, nullable = false)
    private String firstName;

    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = maxLength, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static int getMaxLength() {
        return maxLength;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
