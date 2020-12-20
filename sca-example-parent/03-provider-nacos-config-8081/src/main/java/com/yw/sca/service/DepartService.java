package com.yw.sca.service;

import com.yw.sca.entity.Depart;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 18:49
 */
public interface DepartService {
    boolean save(Depart depart);
    boolean deleteById(int id);
    boolean update(Depart depart);
    Depart findById(int id);
    List<Depart> list();
}
