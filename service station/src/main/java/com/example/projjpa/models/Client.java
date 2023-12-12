package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Клиенты")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;
    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Nationalized
    @Column(name = "lastname", length = 50)
    private String lastname;

    @Nationalized
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Nationalized
    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Nationalized
    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Nationalized
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "idClient")
    private Set<Car> cars = new LinkedHashSet<>();

}
