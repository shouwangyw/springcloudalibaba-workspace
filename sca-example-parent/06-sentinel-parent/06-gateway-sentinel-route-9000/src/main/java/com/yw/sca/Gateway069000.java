package com.yw.sca;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yangwei
 * @date 2020-12-12 19:13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Gateway069000 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway069000.class, args);
        initRule();
        initBlockHandlers();
    }

    private static void initRule() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(gatewayFlowRule());
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 对名称为 staff_route 的路由规则进行限流
     */
    private static GatewayFlowRule gatewayFlowRule() {
        // 定义一个 Gateway 限流规则实例
        GatewayFlowRule rule = new GatewayFlowRule();
        // 指定规则模式是 route 限流，其为默认值
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID);
        // 指定 sentinel 资源名称为 路由规则id
        rule.setResource("staff_route");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        return rule;
    }

    /**
     * 路由限流降级
     */
    private static void initBlockHandlers() {
        GatewayCallbackManager.setBlockHandler((exchange, e) -> {
            // 从请求中获取url
            URI uri = exchange.getRequest().getURI();
            // 将响应数据写入到map
            Map<String, Object> map = new HashMap<>(2);
            map.put("uri", uri);
            map.put("msg", "访问量过大，请稍后再试");
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(map));
        });
    }
}
