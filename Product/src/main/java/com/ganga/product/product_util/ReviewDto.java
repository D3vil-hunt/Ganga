package com.ganga.product.product_util;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
    @NotNull
    private  Long id;
    @NotNull
    private  String subject;
    @NotNull
    private  String body;
}
