package com.yw.sca;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication
public class TokenServer069000 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TokenServer069000.class, args);
        // ServerTransportConfig 集群流控中Token Server与Client通信相关的配置
        ClusterServerConfigManager.loadGlobalTransportConfig(
                // 指定当前Token Server的端口号
                new ServerTransportConfig().setPort(9999));
        // 指定当前Token Server的管理范围，即server/client的微服务名称
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("msc-cluster-token"));
        // 创建并启动一个Token Server
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer();
        tokenServer.start();
    }
}
