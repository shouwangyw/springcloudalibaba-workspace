package com.yw.sca.service;

import com.yw.sca.entity.Depart;
import com.yw.sca.repository.DepartRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 18:52
 */
// 该标签等价于 <dubbo:service/>
@DubboService
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

    @Override
    public Depart findById(int id) {
        if (departRepository.existsById(id)) {
//            // JPA中的getOne()方法默认是按照延迟加载执行的，在远程调用请求访问时会出现问题
//            return departRepository.getOne(id);
            return departRepository.getDepartById(id);
        }
        return new Depart().setName("no this depart");
    }

    @Override
    public List<Depart> list() {
        return departRepository.findAll();
    }
}
