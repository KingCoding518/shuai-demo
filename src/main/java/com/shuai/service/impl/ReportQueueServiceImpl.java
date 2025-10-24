package com.shuai.service.impl;

import com.shuai.domain.po.ReportTask;
import com.shuai.service.ReportQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author: KingCoding
 * @Date: 2025/10/24
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportQueueServiceImpl implements ReportQueueService {

    private static final String QUEUE_KEY = "report:queue";
    private static final String TASK_KEY_PREFIX = "report:task:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /** 任务入队 **/
    public String enqueueTask(String userId, String params) {
        String taskId = UUID.randomUUID().toString();
        ReportTask task = ReportTask.builder()
                .taskId(taskId)
                .userId(userId)
                .params(params)
                .status("QUEUED")
                .createTime(System.currentTimeMillis())
                .updateTime(System.currentTimeMillis())
                .build();

        // 存储任务详情
        redisTemplate.opsForHash().putAll(TASK_KEY_PREFIX + taskId, toMap(task));
        // 入队
        redisTemplate.opsForList().rightPush(QUEUE_KEY, taskId);
        return taskId;
    }

    /** 查询任务状态 **/
    public ReportTask getTaskStatus(String taskId) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(TASK_KEY_PREFIX + taskId);
        if (map.isEmpty()) return null;
        return fromMap(map);
    }

    @Override
    public void updateStatus(String taskId, String running, Object o, Object o1) {

    }

    private Map<String, String> toMap(ReportTask task) {
        Map<String, String> map = new HashMap<>();
        map.put("taskId", task.getTaskId());
        map.put("userId", task.getUserId());
        map.put("params", task.getParams());
        map.put("status", task.getStatus());
        map.put("resultPath", Optional.ofNullable(task.getResultPath()).orElse(""));
        map.put("errorMsg", Optional.ofNullable(task.getErrorMsg()).orElse(""));
        map.put("createTime", task.getCreateTime().toString());
        map.put("updateTime", task.getUpdateTime().toString());
        return map;
    }

    private ReportTask fromMap(Map<Object, Object> map) {
        return ReportTask.builder()
                .taskId((String) map.get("taskId"))
                .userId((String) map.get("userId"))
                .params((String) map.get("params"))
                .status((String) map.get("status"))
                .resultPath((String) map.get("resultPath"))
                .errorMsg((String) map.get("errorMsg"))
                .createTime(Long.parseLong((String) map.get("createTime")))
                .updateTime(Long.parseLong((String) map.get("updateTime")))
                .build();
    }

    /** 更新任务状态 **/
    public void updateStatus(String taskId, String status, String resultPath, String errorMsg) {
        String key = TASK_KEY_PREFIX + taskId;
        redisTemplate.opsForHash().put(key, "status", status);
        redisTemplate.opsForHash().put(key, "updateTime", String.valueOf(System.currentTimeMillis()));
        if (resultPath != null) redisTemplate.opsForHash().put(key, "resultPath", resultPath);
        if (errorMsg != null) redisTemplate.opsForHash().put(key, "errorMsg", errorMsg);
    }
}
