package com.yw.sca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2020-12-24 22:03
 */
@RestController
public class GatewayFallbackController {
    @GetMapping("/fallback")
    public String fallbackHandler() {
        return "This is the Gateway Fallback";
    }
}
