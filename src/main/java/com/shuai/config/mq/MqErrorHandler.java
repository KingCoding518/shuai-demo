package com.shuai.config.mq;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @Author: KingCoding
 * @Date: 2025/7/24
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MqErrorHandler implements RabbitListenerErrorHandler {

    // private final RabbitTemplate rabbitTemplate;
    private final RabbitMqHelper rabbitMqHelper;

    @Override
    public Object handleError(Message message, Channel channel, org.springframework.messaging.Message<?> amqpMessage,
                              ListenerExecutionFailedException e) throws Exception {
        log.error("消费失败，消息将投递到错误队列:{}", amqpMessage.getPayload());
        // 这里你可以自定义错误队列和路由键
        rabbitMqHelper.send("error.topic", "error.shuai-demo", amqpMessage.getPayload());
        // 返回null表示不再处理
        return null;
    }
}
