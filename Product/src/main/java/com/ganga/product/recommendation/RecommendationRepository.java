package com.ganga.product.recommendation;

import com.ganga.product.entity.Category;
import com.ganga.product.entity.Recommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {
    public Set<Recommendation> findByRecommendationIn(Set<String> category);
}
