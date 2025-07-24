package com.shuai.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: KingCoding
 * @Date: 2025/6/23
 * @Description:
 */
@EnableScheduling
@Configuration
@Slf4j
public class TestScheduled {

    /**
     * 每隔1分钟执行一次。
     */
    // @Scheduled(fixedRate = 1000 * 60)
    public void runScheduleFixedRate() throws InterruptedException {
        log.info("==========={}", LocalDateTime.now());
        Thread.sleep(10000);
        log.info(";lllllllll我是每一分钟执行一次");
    }

    /**
     * 每隔1分钟执行一次。
     */
    // @Scheduled(fixedRate = 1000 * 50)
    public void runScheduleFixedRate1() {

        ExecutorService service = Executors.newFixedThreadPool(6);
        // 模拟并发执行 6 个子任务
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            service.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("任务 " + taskId + " 开始执行，线程：" + threadName);
                try {
                    Thread.sleep(3000); // 模拟任务耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("任务 " + taskId + " 被中断");
                }
                System.out.println("任务 " + taskId + " 执行完毕，线程：" + threadName);
            });
        }

        System.out.println("定时任务结束：" + LocalDateTime.now());
    }

    // private int count = 0;
    //
    // @Scheduled(fixedRate = 5000)
    // public void brokenTask() throws InterruptedException {
    //     System.out.println("任务开始：sleep forever");
    //     Thread.sleep(99999999); // 故意卡住线程
    //     System.out.println("任务结束");
    // }
}
