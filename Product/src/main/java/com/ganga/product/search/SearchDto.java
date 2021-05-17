package com.ganga.product.search;

import com.ganga.product.entity.Review;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class SearchDto {

    @NotNull
    private String id;
    @NotNull
    private String displayName;
    @NotNull
    private String shortDescription;
    @NotNull
    private String description;
    @NotNull
    private String category;
    @NotNull
    private Integer price;
    @NotNull
    private Integer discount;
    @NotNull
    private String deliveryCharge;
    private Set<Review> reviews;
    private String image;
    private Integer offerPrice;
}
