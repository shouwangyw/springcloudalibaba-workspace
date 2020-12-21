package com.yw.sca.service;

import com.yw.sca.entity.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign接口名及接口中的方法名一般会与业务接口的相同，但并不是必须的。
 * Feign接口名可以随意，方法名也可以随意，但方法参数及返回值必须与业务接口中相应方法的相同。
 */
@FeignClient(value = "msc-provider-depart") //该参数指定的是提供者的微服务名称，这个名称也称为Feign客户端名称
@RequestMapping("/provider/depart")
public interface DepartService {
    @PostMapping("/save")
    boolean save(@RequestBody Depart depart);
    @DeleteMapping("/del/{id}")
    boolean deleteById(@PathVariable("id") int id);
    @PutMapping("/update")
    boolean update(@RequestBody Depart depart);
    @GetMapping("/get/{id}")
    Depart findById(@PathVariable("id") int id);
    @GetMapping("list")
    List<Depart> list();
}
