package com.ganga.product.recommendation;

import com.ganga.product.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService{

    private final RecommendationRepository recommendationRepository;
    private final Utility utility;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, Utility utility) {
        this.recommendationRepository = recommendationRepository;
        this.utility = utility;
    }


    @Override
    public List<RecommendationDto> getRecommendation() {

        //recommendationRepository.

        return null;
    }
}
