package com.shuai.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: KingCoding
 * @Date: 2025/6/23
 * @Description:
 */
@EnableScheduling
@Configuration
@Slf4j
public class TestScheduled2 {

    /**
     * 每隔1分钟执行一次。
     */
    // @Scheduled(fixedRate = 1000 * 60)
    public void runScheduleFixedRate() {

        log.info("我是每一分钟执行一次2");
    }

    /**
     * 每隔1分钟执行一次。
     */
    // @Scheduled(fixedRate = 1000 * 60)
    public void runScheduleFixedRate1() {

        log.info("========我是每一分钟执行一次2");
    }

}
