package com.example.projjpa.services.car;

import com.example.projjpa.models.Car;
import com.example.projjpa.models.Client;
import com.example.projjpa.models.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface CarService {
    boolean addCar(Car car, Client client, MultipartFile file);
    Set<Order> getOrders(Car car);
    Car refresh(Car car);
    Car findFirstById(Long id);
}
