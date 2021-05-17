package com.ganga.product.product_util;

import com.ganga.product.entity.Product;
import com.ganga.product.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductUtilServiceImpl implements ProductUtilityService {

    private final ProductUtilityRepository productUtilityRepository;
    private final com.ganga.product.util.Utility util;
    private final Mapper mapper;

    @Autowired
    public ProductUtilServiceImpl(ProductUtilityRepository productUtilityRepository, com.ganga.product.util.Utility util) {
        this.productUtilityRepository = productUtilityRepository;
        this.util = util;
        this.mapper = util.getDozerBeanMapper();
    }

    @Override
    public void addReview(Long pId, ReviewDto reviewDto) {
        Product product = productUtilityRepository.findById(pId).get();
        Set<Review> reviews = product.getReviews();
        if(reviews == null) {
            reviews = new HashSet<>();
        }
        reviews.add(mapper.map(reviewDto, Review.class));
        productUtilityRepository.save(product);
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productUtilityRepository.save(mapper.map(productDto, Product.class));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Mapper mapper  = util.getDozerBeanMapper("dozer_mapping.xml");
        List<ProductDto> list =  ((List<Product>) productUtilityRepository.findAll()).stream()
                .map(element -> {
                    ProductDto dto = mapper.map(element, ProductDto.class);
                    if(dto.getDiscount() != null){
                        dto.setOfferPrice(
                                (int) ( (100 - dto.getDiscount()) * 0.01 * dto.getPrice() )
                        );
                        return dto;
                    }else{
                        dto.setDiscount(0);
                        dto.setOfferPrice(0);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return list;
    }
    public ProductDto getProductByID(Long id){
        Mapper mapper = util.getDozerBeanMapper("dozer_mapping.xml");
        Optional<Product> optional = productUtilityRepository.findById(id);
        if(optional.isPresent()){
            ProductDto dto = mapper.map(optional.get(), ProductDto.class);
            if(dto.getDiscount() != null){
                dto.setOfferPrice(
                        (int) ( (100 - dto.getDiscount()) * 0.01 * dto.getPrice() )
                );
                return dto;
            }else{
                dto.setDiscount(0);
                dto.setOfferPrice(0);
            }
            return dto;
        }
        return null;
    }
}
