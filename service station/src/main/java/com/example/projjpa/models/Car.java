package com.example.projjpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Автомобили")
@ToString(exclude = "picture")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

@Pattern(message = "Не корректный VIN",regexp = "\\b[(A-H|J-N|P|R-Z|0-9)]{17}\\b")
    @Nationalized
    @Column(name = "VIN", nullable = false, length = 17)
    private String vin;

  @NotBlank(message = "Номер не должен быть пустым")
    @Nationalized
    @Column(name = "number", length = 6)
    private String number;
  @NotBlank(message = "Марка не должена быть пустой")
  @Size(max = 50, message = "Слишком длинное")
    @Nationalized
    @Column(name = "manufacturer", nullable = false, length = 50)
    private String manufacturer;
    @Size(max = 50, message = "Слишком длинное")
    @NotBlank(message = "Марка не должена быть пустой")
    @Nationalized
    @Column(name = "model", length = 50)
    private String model;

    @Nationalized
    @Column(name = "submodel", length = 50)
    private String submodel;
    @Min(value =1886 ,message = "Некорректный год")
    @Column(name = "\"year\"", nullable = false)
    private Integer year;
    @Pattern(message = "Не корректный регион",regexp = "\\d{2,3}")
    @Nationalized
    @Column(name = "region", nullable = false, length = 3)
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client idClient;

    @Column(name = "picture")
    private byte[] picture;

    @OneToMany(mappedBy = "car")
    private Set<Order> orders = new LinkedHashSet<>();
    public String getDecodedImage(){
        byte[] src= Base64.getEncoder().encode(this.picture);
        return new String(src);
    }
}
