package com.shuai.service.impl;

import com.shuai.service.ReportGenerateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: KingCoding
 * @Date: 2025/10/24
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportGenerateServiceImpl implements ReportGenerateService {

    public String generate(String params) throws InterruptedException {
        System.out.println("开始生成报告: " + params);
        Thread.sleep(5000L); // 模拟耗时任务
        System.out.println("生成完毕: " + params);
        return "/files/report-" + params + ".pdf";
    }
}
