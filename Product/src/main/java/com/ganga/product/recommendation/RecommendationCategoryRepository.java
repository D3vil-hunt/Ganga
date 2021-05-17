package com.ganga.product.recommendation;

import com.ganga.product.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecommendationCategoryRepository extends CrudRepository<Category,Long> {
    Set<Category> findByCategoryIn(Set<String> categories);
}
