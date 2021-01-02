package com.yw.sca.service;

import com.yw.sca.entity.Stock;
import com.yw.sca.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangwei
 * @date 2021-01-01 20:00
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public boolean addStock(Stock stock) {
        Stock originalStock = getStockByName(stock.getName());
        originalStock.setTotal(originalStock.getTotal() + stock.getTotal());
        return stockRepository.save(originalStock) != null;
    }

    @Override
    public Stock getStockByName(String name) {
        Stock stock = stockRepository.findByName(name);
        return stock == null ? new Stock(null, name, 0) : stock;
    }
}
