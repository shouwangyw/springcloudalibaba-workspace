package com.yw.sca.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yangwei
 * @date 2021-01-02 17:53
 */
@Slf4j
@Component
// Sink 类是绑定器
@EnableBinding(Sink.class)
public class MsgConsumer {
//    /**
//     * 为指定名称的管道添加监听器，只要管道中的数据发生变化，就会触发该方法执行
//     */
//    @StreamListener(Sink.INPUT)
//    public void receiveMsg(Object message) {
//        log.info("==>> message: {}", message);
//    }

//    /**
//     * 当指定管道中的数据流发生变化，就会激活当前消费服务
//     */
//    @ServiceActivator(inputChannel = Sink.INPUT)
//    public void receiveMsg2(Object message) {
//        log.info("==>> ServiceActivator receive message: {}", message);
//    }

    @Autowired
    @Qualifier(Sink.INPUT)
    private SubscribableChannel channel;

    @PostConstruct
    public void init() {
        channel.subscribe(message -> {
            byte[] payload = (byte[]) message.getPayload();

            System.out.println(new String(payload) + ", " + message.getHeaders());
        });
    }
}
