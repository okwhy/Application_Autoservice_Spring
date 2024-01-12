package com.example.projjpa.repos;

import com.example.projjpa.models.Order;

import java.util.List;

public interface OrderJpaRepo extends CustomRepository<Order,Long> {
    List<Order> findByJobs_DescriptionNotNullAndEndingNullAndBeginningNotNull();
    boolean existsByJobs_DescriptionNotNullAndEndingNullAndBeginningNotNull();
    boolean existsByBeginningIsNull();

    boolean existsByEndingNullAndBeginningNotNull();



    List<Order> findAllByBeginningIsNull();
    List<Order> findByEndingNullAndBeginningNotNull();
}
