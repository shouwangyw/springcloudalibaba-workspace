package com.yw.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Consumer068280 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer068280.class, args);
    }
}
