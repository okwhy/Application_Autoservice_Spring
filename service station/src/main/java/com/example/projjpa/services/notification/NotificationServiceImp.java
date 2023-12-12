package com.example.projjpa.services.notification;

import com.example.projjpa.models.Client;
import com.example.projjpa.models.Notification;
import com.example.projjpa.repos.NotificationJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
public class NotificationServiceImp implements NotificationService {
    private final NotificationJpaRepo notificationJpaRepo;

    @Override
    public Notification getById(Long id) {
        return notificationJpaRepo.findById(id).orElseThrow();
    }

    @Override
    public void send(String msg,Client client) {
        Notification notification=new Notification();
        notification.setDate(LocalDateTime.now());
        notification.setUserid(client);
        notification.setMaininfo(msg);
        notificationJpaRepo.save(notification);
    }

    @Override
    public List<Notification> recieve(Client client) {
        return notificationJpaRepo.findByUseridAndHiddenOrderByDateAscSeenAsc(client,false);
    }

    @Override
    public void read(Notification notification) {
        notification.setSeen(true);
        notificationJpaRepo.save(notification);
    }

    @Override
    public void hide(Notification notification) {
        notification.setHidden(true);
        this.read(notification);
    }

    @Override
    public void readall(Client client) {
        List<Notification>notifications=notificationJpaRepo
                .findByUseridAndSeen(client,false);
        for(Notification n:notifications){
            n.setSeen(true);
        }
        notificationJpaRepo.saveAll(notifications);

    }

    @Override
    public void hideall(Client client) {
        List<Notification>notifications=notificationJpaRepo
                .findByUseridAndHidden(client,false);
        for(Notification n:notifications){
            n.setHidden(true);
        }
        notificationJpaRepo.saveAll(notifications);
    }

    @Override
    public long count(Client client) {
        return notificationJpaRepo.countByUseridAndSeen(client,false);
    }
}
