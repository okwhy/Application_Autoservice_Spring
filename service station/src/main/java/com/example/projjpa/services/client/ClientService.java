package com.example.projjpa.services.client;

import com.example.projjpa.models.Car;
import com.example.projjpa.models.Client;

import java.util.Set;

public interface ClientService {
    Client authentificate(String login,String password);
    Set<Car> findCars(Client client);
    Client refresh(Client client);
    boolean canCreate(String login);
    Client register(Client client);
}
