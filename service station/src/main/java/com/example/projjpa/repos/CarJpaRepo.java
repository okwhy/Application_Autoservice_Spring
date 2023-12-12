package com.example.projjpa.repos;

import com.example.projjpa.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarJpaRepo extends CustomRepository<Car,Long> {
    boolean existsByVin(String vin);
    Car findFirstById(Long id);
}
