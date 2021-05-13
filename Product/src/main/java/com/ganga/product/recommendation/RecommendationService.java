package com.ganga.product.recommendation;

import com.ganga.product.entity.Recommendation;

import java.util.List;

public interface RecommendationService {
    public List<RecommendationDto> getRecommendation();
}
