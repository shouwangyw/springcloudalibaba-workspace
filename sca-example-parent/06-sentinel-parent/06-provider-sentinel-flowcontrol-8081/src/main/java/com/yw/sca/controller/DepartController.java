package com.yw.sca.controller;

import com.yw.sca.entity.Depart;
import com.yw.sca.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangwei
 * @date 2020-12-12 18:56
 */
@RestController
@RequestMapping("/provider/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart) {
        return departService.save(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable("id") int id) {
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

    @GetMapping("/all")
    public List<Depart> all() {
        return departService.list();
    }
}
