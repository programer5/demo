package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.demo.domain.Store;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    
    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }

    public Store findOne(Long id) {
        return em.find(Store.class, id);
    }

    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class)
            .getResultList();
    }

    
}
