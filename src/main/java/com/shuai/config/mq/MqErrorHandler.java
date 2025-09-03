// package com.shuai.config.mq;
//
// import com.rabbitmq.client.Channel;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
// import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
// import org.springframework.stereotype.Component;
//
// import static com.shuai.constants.MqConstants.Exchange.ERROR_EXCHANGE;
// import static com.shuai.constants.MqConstants.Key.ERROR_KEY_PREFIX;
//
// /**
//  * @Author: KingCoding
//  * @Date: 2025/7/24
//  * @Description:
//  */
// @Slf4j
// @RequiredArgsConstructor
// @Component
// public class MqErrorHandler implements RabbitListenerErrorHandler {
//
//     // private final RabbitTemplate rabbitTemplate;
//     private final RabbitMqHelper rabbitMqHelper;
//
//     @Override
//     public Object handleError(Message message, Channel channel, org.springframework.messaging.Message<?> amqpMessage,
//                               ListenerExecutionFailedException e) {
//         log.error("消费失败，消息将投递到错误队列:{}", amqpMessage.getPayload());
//         // 获取重试次数
//         Integer retryCount = (Integer) message.getMessageProperties().getHeaders().getOrDefault("x-retry-count", 0);
//
//         if (retryCount >= 3) {
//             log.error("消息重试超过3次，丢弃消息: {}", amqpMessage.getPayload());
//             // 可以在这里做持久化记录或报警
//             return null;
//         }
//
//         // 增加重试次数
//         retryCount++;
//         message.getMessageProperties().getHeaders().put("x-retry-count", retryCount);
//         rabbitMqHelper.sendHeaderMessage(ERROR_EXCHANGE, ERROR_KEY_PREFIX + "shuai-demo", amqpMessage.getPayload(), message.getMessageProperties().getHeaders());
//         // 返回null表示不再处理
//         return null;
//     }
// }
