package com.ganga.product.recommendation;

import com.ganga.product.entity.Category;
import com.ganga.product.entity.Product;
import com.ganga.product.entity.Purchase;
import com.ganga.product.entity.Recommendation;
import com.ganga.product.product_util.ProductDto;
import com.ganga.product.product_util.ProductUtilityRepository;
import com.ganga.product.purchase.PurchaseService;
import com.ganga.product.util.Utility;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService{

    private final RecommendationCategoryRepository recommendationCategoryRepository;
    private final Utility utility;
    private final  PurchaseService purchaseService;
    private final ProductUtilityRepository productUtilityRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationCategoryRepository recommendationCategoryRepository, Utility utility,
                                     PurchaseService purchaseService, ProductUtilityRepository productUtilityRepository) {
        this.recommendationCategoryRepository = recommendationCategoryRepository;
        this.utility = utility;
        this.purchaseService = purchaseService;
        this.productUtilityRepository = productUtilityRepository;
    }



    @Override
    public Set<String> getRecommendationForPurchasedProductsOfPastThirtyDays() {
        Set<Purchase> purchases = purchaseService.getThirtyDaysOfPurchase();
        Set<Long> productsPurchased = purchases.stream()
                .map(element -> element.getProductId())
                .collect(Collectors.toSet());
        List<Product> products = productUtilityRepository.findAllByIdIn(productsPurchased);
        Set<String> categoryForRecommendations = products.stream()
                .map(element -> element.getCategory())
                .collect(Collectors.toSet());
        Set<Category> recommendations = recommendationCategoryRepository.findByCategoryIn(categoryForRecommendations);
        List<Set<Recommendation>> list = new ArrayList<>();
        Set<String> allRec = new HashSet<>();
        for(Category cat : recommendations ){
            list.add(cat.getRecommendations());
        }
        for(Set<Recommendation> r : list ){
            for(Recommendation recommendation : r){
                allRec.add(recommendation.getRecommendation());
            }
        }
        return allRec;
    }

    public void addRecommendation(RecommendationDto dto){
        Mapper mapper = utility.getDozerBeanMapper();
        Category category = new Category();
        category.setCategory(dto.getCategory());
        Set<Recommendation> recommendationSet= new HashSet<>();
        Recommendation recommendation = new Recommendation();
        recommendation.setRecommendation(dto.getRecommendation());
        recommendationSet.add(recommendation);
        category.setRecommendations(recommendationSet);
        recommendationCategoryRepository.save(category);
    }

    @Override
    public List<ProductDto> getRecommendedProducts() {
        Mapper mapper = utility.getDozerBeanMapper("dozer_mapping.xml");
        Set<String> recommendations = getRecommendationForPurchasedProductsOfPastThirtyDays();
        return productUtilityRepository.findDistinctByCategoryIn(recommendations)
                .stream()
                .map(element ->{

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
    }
}
