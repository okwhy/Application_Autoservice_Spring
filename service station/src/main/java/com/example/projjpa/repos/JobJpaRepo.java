package com.example.projjpa.repos;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;
import com.example.projjpa.models.Order;

import java.util.List;

public interface JobJpaRepo extends CustomRepository<Job,Long>{
    List<Job> findByOrderNumAndIdNot(Order orderNum, Long id);

//    List<Job> findByDescriptionNullAndEmployee(Employee employee);
//
//
//    boolean existsByDiscountNullAndEmployee(Employee employee);
    boolean existsByOrderNumAndDescriptionIsNull(Order order);

    boolean existsByStartedAndDescriptionNullAndEmployee(Boolean started, Employee employee);


    List<Job> findByStartedAndDescriptionNullAndEmployee(Boolean started,Employee employee);
}
