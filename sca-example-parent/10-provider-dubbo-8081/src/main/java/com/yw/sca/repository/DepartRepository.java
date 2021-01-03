package com.yw.sca.repository;

import com.yw.sca.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangwei
 * @date 2020-12-12 18:48
 */
public interface DepartRepository extends JpaRepository<Depart, Integer> {
    Depart getDepartById(int id);
}
