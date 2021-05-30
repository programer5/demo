package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter @Setter
public class Store {
    
    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String storeName;

    private String storeOwnerName;
    
    @OneToMany(mappedBy = "store")
    private List<Order> orders = new ArrayList<>();
}
