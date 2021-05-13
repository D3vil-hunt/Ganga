package com.ganga.product.deals;

import com.ganga.product.entity.TodaysDeal;
import org.springframework.data.repository.CrudRepository;

public interface DealsRepository extends CrudRepository<TodaysDeal,Long> {
}
