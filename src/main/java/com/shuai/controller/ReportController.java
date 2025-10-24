package com.shuai.controller;

import com.shuai.domain.po.ReportTask;
import com.shuai.service.ReportQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: KingCoding
 * @Date: 2025/10/24
 * @Description:
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportQueueService reportQueueService;

    /** 提交报告生成任务 **/
    @PostMapping("/generate")
    public String generate(@RequestParam String userId, @RequestParam String params) {
        String taskId = reportQueueService.enqueueTask(userId, params);
        return "任务已提交，taskId = " + taskId;
    }

    /** 查询任务状态 **/
    @GetMapping("/status/{taskId}")
    public ReportTask status(@PathVariable String taskId) {
        return reportQueueService.getTaskStatus(taskId);
    }
}
