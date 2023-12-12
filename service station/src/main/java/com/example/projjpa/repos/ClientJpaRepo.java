package com.example.projjpa.repos;

import com.example.projjpa.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepo extends CustomRepository<Client,Long> {
    Client findFirstByLoginAndPassword(String login,String password);
    Client findFirstById(Long id);
    boolean existsByLogin(String login);

}
