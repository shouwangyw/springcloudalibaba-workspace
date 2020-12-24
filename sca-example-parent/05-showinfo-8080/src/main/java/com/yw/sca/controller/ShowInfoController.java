package com.yw.sca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangwei
 * @date 2020-12-24 07:57
 */
@RestController
@RequestMapping("/info")
public class ShowInfoController {
    @RequestMapping("header")
    public String headerHandler(HttpServletRequest request) {
        return "X-Request-Red: " + request.getHeader("X-Request-Red");
    }

    @RequestMapping("/uri")
    public String uriHandler(HttpServletRequest request) {
        return "uri: " + request.getRequestURI();
    }

    @RequestMapping("/param")
    public String paramHandler(String color) {
        return "color: " + color;
    }

    @RequestMapping("/time")
    public String timeHandler() {
        return "time: " + System.currentTimeMillis();
    }
}
