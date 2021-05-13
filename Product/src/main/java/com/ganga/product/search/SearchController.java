package com.ganga.product.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/search_product")
@CrossOrigin(origins = "*")
@Slf4j
public class SearchController {

    private final SearchService service;

    @Autowired
    public SearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping(value = "/{productName}", produces = "application/json")
    public List<SearchDto> searchProductsWithName(@PathVariable("productName") String name){
         return service.getProductsByName(name);
    }
}
