package com.yw.sca.service;

import com.yw.sca.entity.Stock;

/**
 * @author yangwei
 * @date 2021-01-01 19:59
 */
public interface StockService {
    boolean addStock(Stock stock);
    Stock getStockByName(String name);
}
