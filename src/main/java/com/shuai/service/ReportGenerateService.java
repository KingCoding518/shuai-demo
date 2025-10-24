package com.shuai.service;

public interface ReportGenerateService {
    String generate(String taskId) throws InterruptedException;
}
