package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Hangar")
public class HangarPlace {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hangar_place", nullable = false)
    private Long id;

    @Column(name = "Beginning_time")
    private LocalDateTime beginningTime;

    @Column(name = "Estimated_time")
    private LocalDateTime estimatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_number")
    private Order orderNumber;

}
