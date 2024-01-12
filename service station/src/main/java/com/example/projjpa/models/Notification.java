package com.example.projjpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Notification {

    @NotNull
    @Column(name = "datesent", nullable = false)
    private LocalDateTime date;

    @NotNull
    @Column(name = "seen", nullable = false)
    private Boolean seen = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private Client userid;

    @Size(max = 200)
    @Nationalized
    @Column(name = "maininfo", length = 200)
    private String maininfo;

    @NotNull
    @Column(name = "hidden", nullable = false)
    private Boolean hidden = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

}
