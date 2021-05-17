package com.ganga.product.recommendation;

import com.ganga.product.product_util.ProductDto;

import java.util.List;
import java.util.Set;

public interface RecommendationService {
    void addRecommendation(RecommendationDto dto);
    Set<String> getRecommendationForPurchasedProductsOfPastThirtyDays();
    List<ProductDto> getRecommendedProducts();
}
