package com.example.projjpa.repos;

import com.example.projjpa.models.HangarPlace;
import com.example.projjpa.models.Order;

public interface HangarPlaceJpaRepo extends CustomRepository<HangarPlace, Long> {
    boolean existsByOrderNumber(Order orderNumber);
    boolean existsByOrderNumberIsNull();
    HangarPlace findFirstByOrderNumberIsNull();

    HangarPlace findByOrderNumber(Order orderNumber);
}
