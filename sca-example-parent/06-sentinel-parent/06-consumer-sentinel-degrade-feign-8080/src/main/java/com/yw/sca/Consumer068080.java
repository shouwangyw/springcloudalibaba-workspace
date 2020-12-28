package com.yw.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Consumer068080 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer068080.class, args);
    }
}
