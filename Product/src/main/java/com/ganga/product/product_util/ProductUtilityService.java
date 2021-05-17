package com.ganga.product.product_util;

import java.util.List;

public interface ProductUtilityService {

    void addReview(Long pId, ReviewDto review);

    void addProduct(ProductDto product);

    List<ProductDto> getAllProducts();

    ProductDto getProductByID(Long id);
}
