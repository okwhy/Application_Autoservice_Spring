package com.example.projjpa.services.order;

import com.example.projjpa.models.*;
import com.example.projjpa.repos.JobJpaRepo;
import com.example.projjpa.repos.OrderJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
public class OrderServiceImp implements OrderService{
    private final OrderJpaRepo orderJpaRepo;
    private final JobJpaRepo jobJpaRepo;

    @Override
    public Order addOrder(Order order, Client client, Car car) {
        Order toAdd=order;
        toAdd.setClient(client);
        toAdd.setCar(car);
        System.out.println(car);
        return orderJpaRepo.save(toAdd);
    }

    @Override
    public void reject(Order order) {
        orderJpaRepo.delete(order);
    }

    @Override
    public boolean existToCheck() {
        return orderJpaRepo.existsByBeginningIsNull();
    }

    @Override
    public List<Order> getOrdersToCheck() {
        return orderJpaRepo.findAllByBeginningIsNull();
    }

    @Override
    public boolean start(Long id, Employee employee, LocalDateTime begtime) {
        Order order=orderJpaRepo.findById(id).orElseThrow();
        order.setBeginning(begtime);
        order.setEmployee(employee);
        orderJpaRepo.save(order);
        return true;
    }

    @Override
    public boolean canBeFinished(Order order) {
        return jobJpaRepo.existsByOrderNumAndDescriptionIsNull(order);
    }

    @Override
    public Order get(Long id) {
        return orderJpaRepo.findById(id).orElseThrow();
    }

    @Override
    public boolean checkUnfinished() {
        return orderJpaRepo.existsByJobs_DescriptionNotNullAndEndingNullAndBeginningNotNull();
    }

    @Override
    public boolean checkOrdersToAdd() {
        return orderJpaRepo.existsByEndingNullAndBeginningNotNull();
    }

    @Override
    public List<Order> getOrdersToAdd() {
        return orderJpaRepo.findByEndingNullAndBeginningNotNull();
    }

    @Override
    public List<Order> getUnfinised() {
        List<Order> orders= orderJpaRepo.findByJobs_DescriptionNotNullAndEndingNullAndBeginningNotNull();
        List<Order> res=orders.stream().filter(x->x.getJobs().size()>0).toList();
        return res;
    }

    @Override
    public Integer totalPrice(Order order) {

        Integer res=0;
        for(Job j:order.getJobs()){
            for(PartsInOrders p: j.getParts()){
                res+=p.getAmount()*p.getIdDelivery().getPrice();
                res-=p.getAmount()*p.getDiscount();
            }
            res+=j.getPrice();
            res-=j.getDiscount();
        }

        return res;
    }

    @Override
    public void finish(Order order) {
        order.setEnding(LocalDateTime.now());
        orderJpaRepo.save(order);
    }
}
