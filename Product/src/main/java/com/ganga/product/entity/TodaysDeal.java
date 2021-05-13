package com.ganga.product.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class TodaysDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int discount;
    @ToString.Exclude
    @OneToOne(targetEntity = Product.class ,mappedBy = "todaysDeal")
    private Product product;

    @Override
    public String toString(){
        return String.format("id= %d, discount= %d, Product= %s",id,discount,product.toString());
    }
}
