package com.yw.sca;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication
public class TokenClient068080 {
    public static void main(String[] args) {
        SpringApplication.run(TokenClient068080.class, args);
        // 指定当前 Token Client
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        // 指定当前 Token Client 所要连接的 Token Server
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        assignConfig.setServerHost("localhost");
        assignConfig.setServerPort(9999);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
