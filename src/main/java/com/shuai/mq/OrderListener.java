package com.shuai.mq;

import com.shuai.domain.po.MqMessageConsumed;
import com.shuai.domain.po.Order;
import com.shuai.service.MqMessageConsumedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: KingCoding
 * @Date: 2025/7/24
 * @Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {

    private final MqMessageConsumedService mqMessageConsumedService;

    // @RabbitListener(bindings = @QueueBinding(
    //         value = @Queue(name = "order.test.queue", durable = "true"),
    //         exchange = @Exchange(name = MqConstants.Exchange.ORDER_EXCHANGE, type = ExchangeTypes.TOPIC),
    //         key = MqConstants.Key.ORDER_KEY
    // ), errorHandler = "mqErrorHandler")
    public void listenOrderMessage(Order order, Message message) {
        // 获取消息ID
        String messageId = message.getMessageProperties().getMessageId();
        MqMessageConsumed mqMessageConsumed = mqMessageConsumedService.selectMqMessageConsumed(messageId);
        if (mqMessageConsumed != null && mqMessageConsumed.getStatus() == 1) {
            return;
        }
        log.info("mq接收到消息啦, 消息ID: {}, 处理的消息是：{}", messageId, order);
        MqMessageConsumed mqMessageConsumed1 = new MqMessageConsumed()
                .setMessageId(messageId)
                .setStatus(0)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());

        mqMessageConsumedService.saveMqMessageConsumed(mqMessageConsumed1);

        try {
            Thread.sleep(30000);
            // System.out.println(1/0);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("消息处理异常...", e);
        }
        log.info("消息处理结束...");
        mqMessageConsumedService.updateMqMessageConsumed(mqMessageConsumed1.getId(), 1);
    }
}
