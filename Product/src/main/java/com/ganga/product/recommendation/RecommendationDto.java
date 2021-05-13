package com.ganga.product.recommendation;

import lombok.Data;

@Data
public class RecommendationDto {
    private Long id;
    private  String displayName;
    private  String shortDescription;
    private  String category;
}
