package com.example.demo.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("F")
@Getter @Setter
public class Food extends Item {
    
    private String manufacturer;
    private String nutritionInformation;
}
