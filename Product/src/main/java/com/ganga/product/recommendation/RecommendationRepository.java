package com.ganga.product.recommendation;

import com.ganga.product.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends CrudRepository<Category,Long> {
}
