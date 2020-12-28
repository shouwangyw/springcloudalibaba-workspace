package com.yw.sca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2020-12-12 19:15
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @GetMapping("/get/{id}")
    public String findById(@PathVariable("id") int id) {
        return "获取到的 depart 的 id 为：" + id;
    }
}
