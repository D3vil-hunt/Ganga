package com.ganga.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String displayName;
    private  String shortDescription;
    private  String description;
    private  String category;
    private  Integer price;
    private  Integer deliveryCharge;
    private  Integer sellerCount;
    private  Float avgRating;
    @ToString.Exclude
    @OneToOne( targetEntity = TodaysDeal.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "deal_id")
    private TodaysDeal todaysDeal;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Set<Review> reviews = new HashSet<>();



}
