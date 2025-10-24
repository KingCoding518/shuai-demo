package com.shuai.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: KingCoding
 * @Date: 2025/10/24
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportTask {

    private String taskId;
    private String userId;
    private String params;
    private String status;      // QUEUED, RUNNING, SUCCESS, FAILED
    private String resultPath;  // 结果路径
    private String errorMsg;
    private Long createTime;
    private Long updateTime;
}
