package com.yw.sca.controller;

import com.yw.sca.entity.Depart;
import com.yw.sca.service.DepartService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 19:15
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @DubboReference // 使用阿里的注解
    private DepartService departService;

    @PostMapping("/save")
    public Boolean save(@RequestBody Depart depart) {
        return departService.save(depart);
    }

    @DeleteMapping("/del/{id}")
    public Boolean delete(@PathVariable("id") int id) {
        return departService.deleteById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart) {
        return departService.update(depart);
    }

    @GetMapping("/get/{id}")
    public Depart findById(@PathVariable("id") int id) {
        return departService.findById(id);
    }

    @GetMapping("/list")
    public List<Depart> list() {
        return departService.list();
    }
}
