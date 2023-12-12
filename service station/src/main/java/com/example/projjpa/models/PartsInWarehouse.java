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
@Table(name = "Запчасти_и_материалы_на_складе")
public class PartsInWarehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "naming", nullable = false, length = 50)
    private String naming;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Nationalized
    @Column(name = "measure", length = 10)
    private String measure;

    @OneToMany(mappedBy = "idDelivery")
    private Set<PartsInOrders> запчастиИМатериалыВЗаказахs = new LinkedHashSet<>();

}
