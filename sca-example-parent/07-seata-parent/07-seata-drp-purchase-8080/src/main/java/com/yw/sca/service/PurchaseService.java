package com.yw.sca.service;

import com.yw.sca.entity.Purchase;

/**
 * @author yangwei
 * @date 2021-01-01 19:59
 */
public interface PurchaseService {
    boolean addPurchase(Purchase purchase);
    Purchase getPurchaseByName(String name);
}
