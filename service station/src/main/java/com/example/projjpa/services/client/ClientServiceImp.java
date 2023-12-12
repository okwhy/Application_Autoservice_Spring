package com.example.projjpa.services.client;

import com.example.projjpa.models.Car;
import com.example.projjpa.models.Client;
import com.example.projjpa.repos.ClientJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Set;

@Data
@Service
public class ClientServiceImp implements ClientService{
    private final ClientJpaRepo clientJpaRepo;

    public Client authentificate(String login,String password){
        Client potentialUser=clientJpaRepo.findFirstByLoginAndPassword(login,password);
        if(potentialUser==null){
            return null;
        }else{
            potentialUser.setPassword(null);
            return potentialUser;
        }
    }

    @Override
    public Set<Car> findCars(Client client) {
        return client.getCars();
    }

    @Override
    public Client refresh(Client cars) {
        return clientJpaRepo.refresh(cars, cars.getId());
    }

    @Override
    public boolean canCreate(String login) {
        return clientJpaRepo.existsByLogin(login);
    }

    @Override
    public Client register(Client client) {
        return clientJpaRepo.save(client);
    }


}
