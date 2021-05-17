package com.ganga.product.product_util;

import com.ganga.product.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ProductUtilityRepository extends CrudRepository<Product,Long> {
    List<Product> findAllByIdIn(Set<Long> idSet);
    List<Product> findDistinctByCategoryIn(Set<String> category);

}
