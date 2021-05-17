package com.ganga.product.purchase;

import com.ganga.product.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    public Set<Purchase> findByPurchaseDateBeforeAndPurchaseDateAfter(Date before, Date after);
}
