package com.bilgeadam.tutorial.springboot.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "label", length = 30, nullable = false)
    private String label;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "street", length = 50, nullable = false)
    private String street;

    @Column(name = "details", length = 250)
    private String details;

    @Column(name = "postal_code", nullable = false)
    private Integer zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public Address() {
    }

    /**
     * A constructor with not noll values
     * @param label label of the address
     * @param country country code
     * @param city city
     * @param street street
     * @param zipCode zip code
     */
    public Address(@NotNull String label,@NotNull String country,@NotNull String city,
                   @NotNull String street,@NotNull Integer zipCode) {
        this.label = label;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id + '\'' +
                ", label='" + label + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", details='" + details + '\'' +
                ", street='" + street + '\'' +
                ", employee=" + employee + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
