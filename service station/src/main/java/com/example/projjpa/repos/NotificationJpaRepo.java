package com.example.projjpa.repos;

import com.example.projjpa.models.Client;
import com.example.projjpa.models.Notification;

import java.util.List;

public interface NotificationJpaRepo extends CustomRepository<Notification,Long>{
    List<Notification> findByUseridAndSeen(Client userid, Boolean seen);

    long countByUseridAndSeen(Client userid, Boolean seen);

    List<Notification> findByUseridAndHidden(Client userid, Boolean hidden);
    List<Notification> findByUseridAndHiddenOrderByDateAscSeenAsc(Client userid, Boolean hidden);


}
