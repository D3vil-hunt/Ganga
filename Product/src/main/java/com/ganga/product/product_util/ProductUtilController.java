package com.ganga.product.product_util;

import com.ganga.product.deals.DealsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/product_util")
@CrossOrigin(origins = "*")
public class ProductUtilController {

    private final ProductUtilityService service;

    @Autowired
    public ProductUtilController(ProductUtilityService service) { this.service = service; }

    @PutMapping(value = "/{productId}/add_review", consumes = "application/json")
    public void addReview(@PathVariable("productId") Long pId, @RequestBody ReviewDto review){
        service.addReview(pId,review);
    }

    @PutMapping(value = "/add_product", consumes = "application/json")
    public void addProduct(@RequestBody ProductDto product){
        service.addProduct(product);
    }

    @GetMapping(value = "/get_all", produces = "application/json")
    public List<ProductDto> getAllProducts(){
        return service.getAllProducts();
    }
}
