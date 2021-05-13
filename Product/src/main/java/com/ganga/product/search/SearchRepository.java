package com.ganga.product.search;

import com.ganga.product.entity.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends CrudRepository<Product,Long> {
    List<Product> findByDisplayNameContaining(String name);
}
