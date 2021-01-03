package com.yw.sca.controller;

import com.yw.sca.producer.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @date 2021-01-02 17:41
 */
@RestController
public class MessageController {
    @Autowired
    private MsgProducer producer;

    @GetMapping("/msg/send")
    public String send(@RequestParam String msg) {
        return producer.sendMsg(msg);
    }

    @GetMapping("/msg/sendMulti")
    public String sendMulti(@RequestParam String msg) {
        return producer.sendMultiMsg(msg);
    }
}
