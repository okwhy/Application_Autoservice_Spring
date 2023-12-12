package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Запчасти_и_материалы_в_заказах")

public class PartsInOrders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_delivery", nullable = false)
    private PartsInWarehouse idDelivery;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_job", nullable = false)
    private Job idJob;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "amount")
    private Integer amount;

}
