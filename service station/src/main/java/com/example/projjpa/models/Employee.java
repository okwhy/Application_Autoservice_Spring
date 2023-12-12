package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="сотрудники")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Column(name = "Surname", nullable = false, length = 50)
    private String surname;

    @Nationalized
    @Column(name = "Lastname", length = 50)
    private String lastname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Nationalized
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Column(name = "fired", nullable = false)
    private Boolean fired = false;

    @Column(name = "Manager")
    private Boolean manager;

    @Nationalized
    @Column(name = "Login", nullable = false, length = 50)
    private String login;

    @Nationalized
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "employee")
    private Set<Job> jobs = new LinkedHashSet<>();

}
