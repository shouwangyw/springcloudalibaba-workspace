package com.yw.sca.controller;

import com.yw.sca.entity.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 19:15
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private RestTemplate restTemplate;
    // 直连
    private static final String SERVICE_PROVIDER = "http://localhost:8081/provider/depart";

    @PostMapping("/save")
    public Boolean save(@RequestBody Depart depart) {
        return restTemplate.postForObject(SERVICE_PROVIDER + "/save", depart, Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") int id) {
        restTemplate.delete(SERVICE_PROVIDER + "/del/" + id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Depart depart) {
        restTemplate.put(SERVICE_PROVIDER + "/update", depart, Boolean.class);
    }

    @GetMapping("/get/{id}")
    public Depart findById(@PathVariable("id") int id) {
        return restTemplate.getForObject(SERVICE_PROVIDER + "/get/" + id, Depart.class);
    }

    @GetMapping("/list")
    public List<Depart> list() {
        return restTemplate.getForObject(SERVICE_PROVIDER + "/list", List.class);
    }
}
