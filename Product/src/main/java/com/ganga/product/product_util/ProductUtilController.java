package com.ganga.product.product_util;

import com.ganga.product.deals.DealsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "get_all", produces = "application/json")
    public List<ProductDto> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable("id") Long id)
    {
        ProductDto dto = service.getProductByID(id);
        if( dto != null){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
