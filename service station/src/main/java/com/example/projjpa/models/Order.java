package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Заказы")
public class Order {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private Car car;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Nationalized
    @Column(name = "description", length = 200)
    private String description;


    @Column(name = "beginning")
    private LocalDateTime beginning;

    @Column(name = "ending")
    private LocalDateTime ending;


    @Nationalized
    @Column(name = "template", length = 70)
    private String template;


    @OneToMany(mappedBy = "orderNum")
    private Set<Job> jobs = new LinkedHashSet<>();

    @Column(name = "price")
    private Integer price;



    public long countCompletedJobs(){

        return this.jobs.stream().filter(x->x.getDescription()!=null).count();
    }

}
