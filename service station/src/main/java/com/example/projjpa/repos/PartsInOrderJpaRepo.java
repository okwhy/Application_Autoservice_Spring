package com.example.projjpa.repos;

import com.example.projjpa.models.Job;
import com.example.projjpa.models.PartsInOrders;
import com.example.projjpa.models.PartsInWarehouse;

public interface PartsInOrderJpaRepo extends CustomRepository<PartsInOrders,Long> {
    PartsInOrders findByidDeliveryAndIdJob(PartsInWarehouse idOnWare, Job idРаботы);

    boolean existsByidDeliveryAndIdJob(PartsInWarehouse idOnWare, Job idРаботы);

}
