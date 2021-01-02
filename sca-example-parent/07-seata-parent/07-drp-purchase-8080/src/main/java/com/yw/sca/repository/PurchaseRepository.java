package com.yw.sca.repository;

import com.yw.sca.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangwei
 * @date 2021-01-01 19:58
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Purchase findByName(String name);
}
