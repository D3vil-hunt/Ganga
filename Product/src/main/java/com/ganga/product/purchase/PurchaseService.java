package com.ganga.product.purchase;

import com.ganga.product.entity.Purchase;

import java.util.Set;

public interface PurchaseService {

    Set<Purchase> getThirtyDaysOfPurchase();
}
