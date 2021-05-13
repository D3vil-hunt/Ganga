package com.ganga.product.deals;

import com.ganga.product.entity.TodaysDeal;
import com.ganga.product.util.Utility;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealsServiceImpl implements DealsService {

    private final DealsRepository repo;
    private final Utility utility;

    @Autowired
    public DealsServiceImpl(DealsRepository repo, Utility utility) {
        this.repo = repo;
        this.utility = utility;
    }

    @Override
    public List<DealsDto> getTodaysDeal() {
        Mapper mapper = utility.getDozerBeanMapper("dozer_mapping.xml");
        return ((List<TodaysDeal>)repo.findAll()).stream()
                .map(element -> mapper.map(element, DealsDto.class))
                .collect(Collectors.toList());
    }


}