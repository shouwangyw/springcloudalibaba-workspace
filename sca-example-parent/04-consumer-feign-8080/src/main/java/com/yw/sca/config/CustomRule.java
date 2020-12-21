package com.yw.sca.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 从所有可用的provider中排除掉指定端口号的provider，剩余provider进行随机选择
 */
public class CustomRule implements IRule {
    private ILoadBalancer lb;
    private List<Integer> excludePorts;

    public CustomRule() {
    }

    public CustomRule(List<Integer> excludePorts) {
        this.excludePorts = excludePorts;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }

    @Override
    public Server choose(Object key) {
        // 获取到所有可用的server
        List<Server> servers = lb.getReachableServers();
        // 获取从所有server中排除指定的端口号后剩余的server
        List<Server> availableServers = this.getAvailableServers(servers);
        // 从剩余server中随机选择一个
        return this.getAvailableRandomServers(availableServers);
    }

    /**
     * 获取从所有server中排除指定的端口号后剩余的server
     */
    private List<Server> getAvailableServers(List<Server> servers) {
        if (excludePorts == null || excludePorts.size() == 0) {
            return servers;
        }

        return servers.stream()
                .filter(server -> excludePorts.stream().noneMatch(port -> server.getPort() == port))
                .collect(Collectors.toList());
    }

    private Server getAvailableRandomServers(List<Server> availableServers) {
        int index = new Random().nextInt(availableServers.size());
        return availableServers.get(index);
    }
}