package com.example.projjpa.services.notification;

import com.example.projjpa.models.Client;
import com.example.projjpa.models.Notification;

import java.util.List;

public interface NotificationService {
    Notification getById(Long id);
    void send(String msg,Client client);
    List<Notification> recieve(Client client);
    void read(Notification notification);
    void hide(Notification notification);
    void readall(Client client);
    void hideall(Client client);
    long count(Client client);
}
