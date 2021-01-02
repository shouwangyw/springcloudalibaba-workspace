package com.yw.sca.controller;

import com.yw.sca.entity.Stock;
import com.yw.sca.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2021-01-01 20:04
 */
@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/stock/add")
    public boolean addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }
}
