package com.example.projjpa.services.parts.orders;

import com.example.projjpa.models.PartsInOrders;
import com.example.projjpa.repos.JobJpaRepo;
import com.example.projjpa.repos.PartsInOrderJpaRepo;
import com.example.projjpa.repos.PartsInWarehouseJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class PartsInOrdersServiceImp implements PartsInOrdersService {
    private final PartsInOrderJpaRepo orderJpaRepo;
    private final PartsInWarehouseJpaRepo warehouseJpaRepo;
    private final JobJpaRepo jobJpaRepo;

    @Override
    public boolean add(PartsInOrders parts) {
        if(parts.getIdDelivery().getAmount()-parts.getAmount()<0){
            return false;
        }
        parts.getIdDelivery().setAmount(parts.getIdDelivery().getAmount()-parts.getAmount()) ;
        warehouseJpaRepo.save(parts.getIdDelivery());
        parts.setDiscount(0);
        if(orderJpaRepo.existsByidDeliveryAndIdJob(parts.getIdDelivery(),parts.getIdJob())){
            PartsInOrders toedit=orderJpaRepo.findByidDeliveryAndIdJob(parts.getIdDelivery(),parts.getIdJob());
            toedit.setAmount(toedit.getAmount()+parts.getAmount());
            orderJpaRepo.save(toedit);
        }else {
            orderJpaRepo.save(parts);
        }
        return true;
    }

    @Override
    public void editDiscount(PartsInOrders parts) {
        orderJpaRepo.save(parts);
    }

    @Override
    public void add(PartsInOrders parts, Long jobId) {
        PartsInOrders toadd=parts;

        toadd.setIdJob(jobJpaRepo.findById(jobId).orElseThrow());

        this.add(toadd);
    }

    @Override
    public void add(PartsInOrders parts, Integer amount) {

    }

    @Override
    public boolean edit(PartsInOrders parts) {
        if(parts.getIdDelivery().getAmount()+
                orderJpaRepo.findById(parts.getId()).orElseThrow().getAmount()
                -parts.getAmount()<0){
            return false;
        }
        parts.getIdDelivery().setAmount(parts.getIdDelivery().getAmount()+
                orderJpaRepo.findById(parts.getId()).orElseThrow().getAmount()-parts.getAmount()) ;
        warehouseJpaRepo.save(parts.getIdDelivery());
        orderJpaRepo.save(parts);
        return true;
    }
}
