package com.yw.sca.fallback;

import com.yw.sca.entity.Depart;
import com.yw.sca.service.DepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 自定义降级类
 *
 * @author yangwei
 * @date 2020-12-25 22:47
 */
@Slf4j
@Component
@RequestMapping("/fallback/consumer/depart") // 必须以 /fallback 开头
public class DepartServiceFallback implements DepartService { // 实现Feign接口

    @Override
    public boolean save(Depart depart) {
        log.info("执行save()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        log.info("执行deleteById()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean update(Depart depart) {
        log.info("执行update()的服务降级处理方法");
        return false;
    }

    @Override
    public Depart findById(int id) {
        log.info("执行findById()的服务降级处理方法");
        return new Depart()
                .setId(id)
                .setName("degrade-feign");
    }

    @Override
    public List<Depart> list() {
        log.info("执行list()的服务降级处理方法");
        return Collections.singletonList(new Depart().setName("no any depart"));
    }
}
