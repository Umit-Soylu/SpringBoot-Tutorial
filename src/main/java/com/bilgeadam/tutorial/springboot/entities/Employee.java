package com.bilgeadam.tutorial.springboot.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "employees")
@Getter @Setter @ToString @NoArgsConstructor @RequiredArgsConstructor
public class Employee {
    @Getter private static final int maxLength = 15;
    @Getter private static final int minLength = 3;
    private static final String regex = "^\\w{" + minLength +"," + maxLength + "}\\Z";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PROTECTED)
    @ToString.Exclude private Integer id;

    @Size(min = minLength, max = maxLength)
    @Pattern(regexp = regex, flags = Pattern.Flag.CASE_INSENSITIVE)
    @NonNull
    @Column(name = "first_name", length = maxLength, nullable = false)
    private String firstName;

    @NonNull
    @Pattern(regexp = regex, flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "last_name", length = maxLength, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Email //https://github.com/hibernate/hibernate-validator/blob/master/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/bv/EmailValidator.java?source=cc
    @Column(name = "email")
    private String email;
}
