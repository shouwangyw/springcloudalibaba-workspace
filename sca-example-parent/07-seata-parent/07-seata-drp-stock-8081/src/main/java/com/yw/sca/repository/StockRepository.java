package com.yw.sca.repository;

import com.yw.sca.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangwei
 * @date 2021-01-01 19:58
 */
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByName(String name);
}
