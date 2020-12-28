package com.yw.sca.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yw.sca.entity.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    private static final String SERVICE_PROVIDER = "http://msc-provider-depart/provider/depart";

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

    /**
     * fallback属性用于指定降级方法名称
     */
    @SentinelResource(fallback = "findByIdFallback")
    @GetMapping("/get/{id}")
    public Depart findById(@PathVariable("id") int id) {
        return restTemplate.getForObject(SERVICE_PROVIDER + "/get/" + id, Depart.class);
    }

    /**
     * 定义降级处理方法
     */
    public Depart findByIdFallback(int id, Throwable e) {
        return new Depart()
                .setId(id)
                .setName("degrade-method-" + id + "-" + e.getMessage());
    }

    @GetMapping("/list")
    public List<Depart> list() {
        return restTemplate.getForObject(SERVICE_PROVIDER + "/list", List.class);
    }

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/discovery")
    public List<String> discoveryHandle() {
        List<String> services = client.getServices();
        for (String serviceName : services) {
            List<ServiceInstance> instances = client.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                String serviceId = instance.getServiceId();
                String host = instance.getHost();
                int port = instance.getPort();
                URI uri = instance.getUri();
                System.out.println("serviceId = " + serviceId);
                System.out.println("host = " + host);
                System.out.println("port = " + port);
                System.out.println("uri = " + uri);
            }
        }
        return services;
    }

    @SentinelResource(value = "paramFlowRule", fallback = "getCompluxFallback")
    @GetMapping("/complux")
    public String getComplux(Integer id, String name) {
        return "complux: " + id + ", " + name;
    }
    /**
     * 降级方法
     */
    public String getCompluxFallback(Integer id, String name) {
        return "complux fallback: " + id + ", " + name;
    }
}
