package com.shuai.config;

import com.shuai.service.ReportGenerateService;
import com.shuai.service.ReportQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: KingCoding
 * @Date: 2025/10/24
 * @Description:
 */
@Slf4j
@Component
public class ReportConsumer implements InitializingBean {
    private static final String QUEUE_KEY = "report:queue";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ReportQueueService reportQueueService;

    @Autowired
    private ReportGenerateService reportGenerateService;

    @Override
    public void afterPropertiesSet() {
        new Thread(this::consumeLoop, "Report-Consumer-Thread").start();
    }

    private void consumeLoop() {
        while (true) {
            try {
                // 每 10 秒阻塞一次（防止连接被 Redis 或中间件断开）
                String taskId = redisTemplate.opsForList().leftPop(QUEUE_KEY, 10, TimeUnit.SECONDS);

                if (taskId == null) continue;
                log.info("报告生成开始...");
                // 更新状态
                reportQueueService.updateStatus(taskId, "RUNNING", null, null);

                try {
                    String resultPath = reportGenerateService.generate(taskId);
                    reportQueueService.updateStatus(taskId, "SUCCESS", resultPath, null);
                } catch (Exception e) {
                    reportQueueService.updateStatus(taskId, "FAILED", null, e.getMessage());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
