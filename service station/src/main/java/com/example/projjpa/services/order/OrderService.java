package com.example.projjpa.services.order;

import com.example.projjpa.models.*;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order addOrder(Order order , Client client, Car car);
    void reject(Order order);
    boolean existToCheck();
    List<Order> getOrdersToCheck();
    boolean start(Long id, Employee employee, LocalDateTime begtime);
    boolean canBeFinished(Order order);
    Order get(Long id);
    boolean checkUnfinished();
    boolean checkOrdersToAdd();
    List<Order> getOrdersToAdd();
    List<Order> getUnfinised();
    Integer totalPrice(Order order);
    void finish(Order order);
}
