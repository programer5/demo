package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Store;
import com.example.demo.repository.StoreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    
    private final StoreRepository storeRepository;

    @Transactional
    public Long join(Store store) {
        
        storeRepository.save(store);
        return store.getId();
    }

    public List<Store> findStores() {
        return storeRepository.findAll();
    }

    public Store findOne(Long storeId) {
        return storeRepository.findOne(storeId);
    }

    @Transactional
    public void update(Long id, String name) {
        Store store = storeRepository.findOne(id);
        store.setStoreName(name);
    }
}
