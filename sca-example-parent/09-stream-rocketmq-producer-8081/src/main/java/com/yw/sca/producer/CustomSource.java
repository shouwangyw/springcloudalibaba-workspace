package com.yw.sca.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author yangwei
 * @date 2021-01-02 19:53
 */
public interface CustomSource {
    String CHANNEL_NAME = "custom";

    @Output(CustomSource.CHANNEL_NAME)
    MessageChannel output();
}
