package com.example.projjpa.services.hangarplace;

import com.example.projjpa.models.HangarPlace;
import com.example.projjpa.models.Order;
import com.example.projjpa.repos.HangarPlaceJpaRepo;
import com.example.projjpa.repos.OrderJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
public class HangarServiceImp implements HangarService {
    private final HangarPlaceJpaRepo hangarPlaceJpaRepo;
    private final OrderJpaRepo orderJpaRepo;
    @Override
    public boolean hasEmpty() {
        return hangarPlaceJpaRepo.existsByOrderNumberIsNull();
    }

    @Override
    public boolean takePlace(Long id,String endDate) {
        LocalDateTime beginning=LocalDateTime.now();
        LocalDateTime ending=LocalDateTime.parse(endDate);
        if(beginning.isAfter(ending)){
            return false;
        }

        HangarPlace hangarPlace=hangarPlaceJpaRepo.findFirstByOrderNumberIsNull();
        hangarPlace.setBeginningTime(beginning);
        hangarPlace.setEstimatedTime(ending);
        hangarPlace.setOrderNumber(orderJpaRepo.findById(id).orElseThrow());
        hangarPlaceJpaRepo.save(hangarPlace);
        return true;
    }

    @Override
    public boolean hasPlace(Order order) {
        return hangarPlaceJpaRepo.existsByOrderNumber(order);
    }

    @Override
    public HangarPlace getByOrder(Order order) {
        return hangarPlaceJpaRepo.findByOrderNumber(order);
    }

    @Override
    public void freePlace(Order order) {
        if(this.hasPlace(order)){
            HangarPlace toedit=hangarPlaceJpaRepo.findByOrderNumber(order);
            HangarPlace clearplace=new HangarPlace();
            clearplace.setId(toedit.getId());
            hangarPlaceJpaRepo.save(clearplace);
        }
    }
}
