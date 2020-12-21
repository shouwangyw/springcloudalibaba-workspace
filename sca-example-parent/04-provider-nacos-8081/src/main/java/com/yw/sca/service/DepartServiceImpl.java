package com.yw.sca.service;

import com.yw.sca.entity.Depart;
import com.yw.sca.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yangwei
 * @date 2020-12-12 18:52
 */
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository departRepository;

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

    @Value("${server.port}")
    private int port;

    @Override
    public Depart findById(int id) {
        if (departRepository.existsById(id)) {
            Depart depart = departRepository.getOne(id);
            depart.setName(depart.getName() + " : " + port);
            return depart;
        }
        return new Depart().setName("no this depart " + port);
    }

//    @Override
//    public Depart findById(int id) {
//        try {
//            TimeUnit.SECONDS.sleep(6);
//        } catch (InterruptedException ignored) {}
//        if (departRepository.existsById(id)) {
//            return departRepository.getOne(id);
//        }
//        return new Depart().setName("no this depart");
//    }

    @Override
    public List<Depart> list() {
        return departRepository.findAll();
    }
}
