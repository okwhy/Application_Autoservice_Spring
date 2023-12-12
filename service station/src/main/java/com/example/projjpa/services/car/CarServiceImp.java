package com.example.projjpa.services.car;

import com.example.projjpa.models.Car;
import com.example.projjpa.models.Client;
import com.example.projjpa.models.Order;
import com.example.projjpa.repos.CarJpaRepo;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@Data
public class CarServiceImp implements CarService{
    private final CarJpaRepo carJpaRepo;
    @Override
    @SneakyThrows
    public boolean addCar(Car car, Client client, MultipartFile file) {
        boolean chk = carJpaRepo.existsByVin(car.getVin());
        if(chk)
        return false;
        car.setPicture(file.getBytes());
        car.setIdClient(client);
        carJpaRepo.save(car);

        return true;
    }

    @Override
    public Set<Order> getOrders(Car car) {
        return car.getOrders();
    }
    @Override
    public Car refresh(Car cars) {
        return carJpaRepo.refresh(cars, cars.getId());
    }

    @Override
    public Car findFirstById(Long id) {
        return carJpaRepo.findFirstById(id);
    }
}
