package com.bilgeadam.tutorial.springboot.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "employees")
//@Getter @Setter @ToString @NoArgsConstructor @RequiredArgsConstructor
@Data @NoArgsConstructor @RequiredArgsConstructor
public class Employee {
    @Getter private static final int maxLength = 15;
    @Getter private static final int minLength = 5;
    @Getter private static final String regex = "^[a-zA-Z]{" + minLength + "," + maxLength + "}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Setter(AccessLevel.PACKAGE)
    @ToString.Exclude private Integer id;

    //@Min(minLength) @Max(maxLength)
    @NonNull
    @Pattern(regexp = regex, flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "first_name", length = maxLength, nullable = false)
    private String firstName;

    //@Min(minLength) @Max(maxLength)
    @NonNull
    @Pattern(regexp = regex, flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "last_name", length = maxLength, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Size(max = 25)
    @OneToMany(orphanRemoval = true, mappedBy = "employee")
    private Set<Address> addresses;

    @Setter(AccessLevel.PACKAGE) private String email;
}
