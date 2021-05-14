package com.ganga.adminservice.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Utility {


    public Mapper getDozerBeanMapper() {
        return new DozerBeanMapper();
    }

    public Mapper getDozerBeanMapper(String... mappingFileUrls) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(mappingFileUrls));
        return mapper;
    }
}
