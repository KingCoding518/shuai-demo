package com.shuai.service;

import com.shuai.domain.po.ReportTask;

public interface ReportQueueService {
    String enqueueTask(String userId, String params);

    ReportTask getTaskStatus(String taskId);

    void updateStatus(String taskId, String running, Object o, Object o1);
}
