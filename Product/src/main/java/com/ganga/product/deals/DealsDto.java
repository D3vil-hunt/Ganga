package com.ganga.product.deals;

import lombok.Data;

@Data
public class DealsDto {
    private Long id;
    private int discount;
    private Long productId;
    private  String displayName;
    private  String shortDescription;
    private  String category;

}
