package com.ganga.product.search;

import java.util.List;


public interface SearchService {
    List<SearchDto> getProductsByName(String name);

}
