package com.ganga.product.search;

import com.ganga.product.product_util.ProductDto;
import com.ganga.product.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService{

    private final SearchRepository repository;
    private final Utility util;
    private Mapper mapper;

    @Autowired
    public SearchServiceImpl(Utility util, SearchRepository repository) {
        this.repository = repository;
        this.util = util;
        this.mapper = util.getDozerBeanMapper();
    }

    @Override
    public List<SearchDto> getProductsByName(String name) {
        return repository.findByDisplayNameContaining(name)
                .stream()
                .map( element ->{
                    SearchDto dto = mapper.map(element, SearchDto.class);
                    if(!Objects.isNull(element.getTodaysDeal())){
                        dto.setDiscount(element.getTodaysDeal().getDiscount());
                    }
                     if(dto.getDiscount() != null){
                        dto.setOfferPrice(
                                (int) ( (100 - dto.getDiscount()) * 0.01 * dto.getPrice() )
                        );
                        return dto;
                    }else{
                        dto.setDiscount(0);
                        dto.setOfferPrice(dto.getPrice());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
