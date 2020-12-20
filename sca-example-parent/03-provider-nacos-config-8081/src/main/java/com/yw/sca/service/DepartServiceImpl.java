package com.yw.sca.service;

import com.yw.sca.entity.Depart;
import com.yw.sca.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 18:52
 */
@RefreshScope
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository departRepository;
    @Value("${depart.name}")
    private String departName;

    @Override
    public boolean save(Depart depart) {
        return departRepository.save(depart) != null;
    }

    @Override
    public boolean deleteById(int id) {
        if (departRepository.existsById(id)) {
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Depart depart) {
        return departRepository.save(depart) != null;
    }

    @Override
    public Depart findById(int id) {
        if (departRepository.existsById(id)) {
            return departRepository.getOne(id).setName(departName);
        }
        return new Depart().setName("no this depart " + departName);
    }

    @Override
    public List<Depart> list() {
        return departRepository.findAll();
    }
}
