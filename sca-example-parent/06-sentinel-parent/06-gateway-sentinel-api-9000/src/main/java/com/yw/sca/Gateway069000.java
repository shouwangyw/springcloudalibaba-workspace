package com.yw.sca;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication
public class Gateway069000 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway069000.class, args);
        initCustomizedApis();
        initRule();
        initBlockHandlers();
    }

    private static void initCustomizedApis() {
        // 定义一个名称为 depart_api 的路由 api
        ApiDefinition departApi = new ApiDefinition("depart_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/consumer/depart/get/**")
                            // 指定该路由api对于请求的匹配策略是 前缀匹配
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        // 定义一个名称为 staff_api 的陆游与 api
        ApiDefinition staffApi = new ApiDefinition("staff_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/consumer/staff/get/2"));
                    add(new ApiPathPredicateItem().setPattern("/consumer/staff/get/3")
                            // 指定该路由api对于请求的匹配策略是 精准匹配
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_EXACT));
                }});

        Set<ApiDefinition> definitions = new HashSet<>();
        definitions.add(departApi);
        definitions.add(staffApi);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    private static void initRule() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        // 这里仅对/consumer/depart/get/**形式的请求进行限流
        rules.add(gatewayFlowRule("depart_api", 1));
        // 这里仅对/consumer/staff/get/2与/consumer/staff/get/3这两个请求进行限流
        rules.add(gatewayFlowRule("staff_api", 2));
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 对名称为 staff_route 的路由规则进行限流
     */
    private static GatewayFlowRule gatewayFlowRule(String apiName, int count) {
        // 定义一个 Gateway 限流规则实例
        GatewayFlowRule rule = new GatewayFlowRule();
        // 指定规则模式为路由api限流
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME);
        // 指定 sentinel 资源名称为 路由规则id
        rule.setResource(apiName);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(count);
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
