package com.example.projjpa.repos;

import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;

public class CustomRepositoryImp <T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImp(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public T refresh(T t,Long id) {

        T managedT= (T) entityManager.find(t.getClass(),id);
        entityManager.refresh(managedT);
        return managedT;
    }
}
