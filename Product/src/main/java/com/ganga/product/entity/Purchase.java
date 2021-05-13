package com.ganga.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
