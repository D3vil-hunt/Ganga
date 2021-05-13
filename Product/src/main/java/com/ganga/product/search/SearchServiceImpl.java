package com.ganga.product.search;

import com.ganga.product.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService{

    private final SearchRepository repository;

    private final Mapper mapper;

    @Autowired
    public SearchServiceImpl(Utility util, SearchRepository repository) {
        this.repository = repository;
        this.mapper = util.getDozerBeanMapper();
    }

    @Override
    public List<SearchDto> getProductsByName(String name) {
        return repository.findByDisplayNameContaining(name)
                .stream()
                .map( element -> mapper.map(element, SearchDto.class))
                .collect(Collectors.toList());
    }

}
