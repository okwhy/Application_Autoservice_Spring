package com.example.projjpa.services.parts.warehouse;

import com.example.projjpa.models.PartsInWarehouse;
import com.example.projjpa.repos.PartsInWarehouseJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class PartsInWarehouseServiceImp implements PartsInWarehouseService {
    private final PartsInWarehouseJpaRepo warehouseJpaRepo;
    @Override
    public List<PartsInWarehouse> get() {

        return warehouseJpaRepo.findAll().stream().filter(x->x.getAmount()>0).toList();
    }
}
