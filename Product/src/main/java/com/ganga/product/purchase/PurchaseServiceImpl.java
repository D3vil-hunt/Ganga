package com.ganga.product.purchase;

import com.ganga.product.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository repository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository repository) {
        this.repository = repository;
    }

    public Set<Purchase> getThirtyDaysOfPurchase(){
        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, - 30);
        Date before30 =new Date(calendar.getTimeInMillis());
        return repository.findByPurchaseDateBeforeAndPurchaseDateAfter(today, before30);
    }
}
