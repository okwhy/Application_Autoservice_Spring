package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Работы")
public class Job {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;


    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Column(name = "description", length = 500)
    private String description;


    @OneToMany(mappedBy = "idJob")
    private Set<PartsInOrders> parts = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_num")
    private Order orderNum;

    @Column(name = "started")
    private Boolean started;

}
