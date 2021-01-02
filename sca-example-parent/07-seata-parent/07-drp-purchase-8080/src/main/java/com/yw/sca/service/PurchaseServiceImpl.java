package com.yw.sca.service;

import com.yw.sca.entity.Purchase;
import com.yw.sca.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangwei
 * @date 2021-01-01 20:00
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public boolean addPurchase(Purchase stock) {
        Purchase originalPurchase = getPurchaseByName(stock.getName());
        originalPurchase.setCount(originalPurchase.getCount() + stock.getCount());
        return purchaseRepository.save(originalPurchase) != null;
    }

    @Override
    public Purchase getPurchaseByName(String name) {
        Purchase purchase = purchaseRepository.findByName(name);
        return purchase == null ? new Purchase(null, name, 0) : purchase;
    }
}
