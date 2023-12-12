package com.example.projjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="КаталогРабот")
public class JobRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "\"min\"", nullable = false)
    private Integer min;

    @Column(name = "\"max\"", nullable = false)
    private Integer max;

}
