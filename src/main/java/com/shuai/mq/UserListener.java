package com.shuai.mq;

import cn.hutool.json.JSONUtil;
import com.shuai.config.mq.RabbitMqHelper;
import com.shuai.constants.MqConstants;
import com.shuai.domain.po.MqMessageConsumed;
import com.shuai.domain.po.User;
import com.shuai.service.MqMessageConsumedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

import static com.shuai.constants.MqConstants.Queue.ERROR_QUEUE_TEMPLATE;

/**
 * @Author: KingCoding
 * @Date: 2025/7/23
 * @Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserListener {

    private final MqMessageConsumedService mqMessageConsumedService;
    private final RabbitMqHelper rabbitMqHelper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "user.test.queue", durable = "true"),
            exchange = @Exchange(name = MqConstants.Exchange.USER_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = MqConstants.Key.USER_KEY
    ), errorHandler = "mqErrorHandler")
    public void listenWriteReplyMessage(User user, Message message) throws Exception {
        // 获取消息ID
        String messageId = message.getMessageProperties().getMessageId();
        MqMessageConsumed mqMessageConsumed = mqMessageConsumedService.selectMqMessageConsumed(messageId);
        if (mqMessageConsumed != null && mqMessageConsumed.getStatus() == 1) {
            return;
        }
        log.info("mq接收到消息啦, 消息ID: {}, 处理的消息是：{}", messageId, user);
        MqMessageConsumed mqMessageConsumed1 = new MqMessageConsumed()
                .setMessageId(messageId)
                .setStatus(0)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());

        mqMessageConsumedService.saveMqMessageConsumed(mqMessageConsumed1);

        try {
            Thread.sleep(3000);
            // System.out.println(1/0);
        } catch (Exception e) {
            mqMessageConsumedService.updateMqMessageConsumed(mqMessageConsumed1.getId(), 2);
            throw new AmqpRejectAndDontRequeueException("用户消息消费失败，放到指定错误队列中...", e);
        }
        log.info("消息处理结束...");
        mqMessageConsumedService.updateMqMessageConsumed(mqMessageConsumed1.getId(), 1);
    }

    public void consumeOrderKnowledgeQueue() {
        while (true) {
            // 拉取一条消息
            Message message = rabbitMqHelper.receive(ERROR_QUEUE_TEMPLATE);
            if (message == null) {
                break;
            }
            try {
                String json = new String(message.getBody(), StandardCharsets.UTF_8);
                User user = JSONUtil.toBean(json, User.class);
                // 获取原 headers
                Map<String, Object> headers = message.getMessageProperties().getHeaders();
                rabbitMqHelper.sendHeaderMessage(
                        MqConstants.Exchange.USER_EXCHANGE,
                        MqConstants.Key.USER_KEY,
                        user,
                        headers);
            } catch (Exception e) {
                // 处理异常
            }
        }
    }
}
