package com.yw.sca.producer;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @date 2021-01-02 17:31
 */
@Component
// Source类为绑定器
@EnableBinding({Source.class, CustomSource.class})
public class MsgProducer {
    /**
     * 必须使用byType注入，因为系统中同时定义了名称为 nullChannel 与 errorChannel 的两个同类型管道
     */
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel channel;

    @Autowired
    @Qualifier(CustomSource.CHANNEL_NAME)
    private MessageChannel customChannel;

    public String sendMsg(String msg) {
        // 创建MessageHeaders
        Map<String, Object> headers = new HashMap<>(1);
        headers.put(MessageConst.PROPERTY_TAGS, "stream-rmq");
        MessageHeaders msgHeaders = new MessageHeaders(headers);
        // 消息由 消息体 与 消息头 两部分构成
        Message<String> message = MessageBuilder.createMessage(msg, msgHeaders);
        // 通过管道将消息发送给MQ
        channel.send(message);
        return msg;
    }

    public String sendMultiMsg(String msg) {
        Map<String, Object> headers = new HashMap<>(1);
        headers.put(MessageConst.PROPERTY_TAGS, "multi-rmq");
        MessageHeaders msgHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(msg, msgHeaders);
        channel.send(message);
        customChannel.send(message);
        return msg;
    }
}
