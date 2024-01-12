package com.example.projjpa.services.parts.orders;

import com.example.projjpa.models.PartsInOrders;

public interface PartsInOrdersService {
    boolean add(PartsInOrders parts);
    void editDiscount(PartsInOrders parts);
    void add(PartsInOrders parts,Long jobId);
    void add(PartsInOrders parts,Integer amount);
    boolean edit(PartsInOrders parts);
}
