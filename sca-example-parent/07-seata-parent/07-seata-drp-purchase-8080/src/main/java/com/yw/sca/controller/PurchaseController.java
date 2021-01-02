package com.yw.sca.controller;

import com.yw.sca.entity.Purchase;
import com.yw.sca.entity.Stock;
import com.yw.sca.service.PurchaseService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangwei
 * @date 2021-01-01 20:04
 */
@RestController
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_URL = "http://msc-stock-service";

    @GlobalTransactional   // 开启全局事务
    @PostMapping("/purchase/add/{deno}")
    public Boolean addPurchase(@RequestBody Purchase purchase, @PathVariable("deno") int deno) {
        purchaseService.addPurchase(purchase);

        int i = 3 / deno;

        Stock stock = new Stock();
        stock.setName(purchase.getName());
        stock.setTotal(purchase.getCount());

        String url = STOCK_SERVICE_URL + "/stock/add";
        return restTemplate.postForObject(url, stock, Boolean.class);
    }
}
