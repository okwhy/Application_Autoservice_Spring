package com.example.projjpa.services.hangarplace;

import com.example.projjpa.models.HangarPlace;
import com.example.projjpa.models.Order;

public interface HangarService {
    boolean hasEmpty();
    boolean takePlace(Long id, String endDate);
    boolean hasPlace(Order order);
    HangarPlace getByOrder(Order order);
    void freePlace(Order order);
}
