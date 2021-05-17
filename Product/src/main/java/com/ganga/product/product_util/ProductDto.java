package com.ganga.product.product_util;

import com.ganga.product.entity.Review;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ProductDto {
    @NotNull
    private  Long id;
    @NotNull
    private  String displayName;
    @NotNull
    private  String shortDescription;
    @NotNull
    private  String description;
    @NotNull
    private  String category;
    @NotNull
    private  Integer price;
    @NotNull
    private  Integer discount;
    @NotNull
    private  Integer deliveryCharge;
    @NotNull
    private  Integer offerPrice;
    @NotNull
    private  Integer sellerCount;
    @NotNull
    private  Float avgRating;
    private  String image;
    private Set<Review> reviews;
}
