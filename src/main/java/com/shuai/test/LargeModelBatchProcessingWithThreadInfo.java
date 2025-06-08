package com.shuai.test;

/**
 * @Author: KingCoding
 * @Date: 2025/6/8
 * @Description:
 */

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LargeModelBatchProcessingWithThreadInfo {
    public static void main(String[] args) throws InterruptedException {
        // 1. 假设我们有10000条数据
        List<Integer> allData = IntStream.rangeClosed(1, 10_000).boxed().collect(Collectors.toList());

        // 2. 分批：每40条为一组
        int batchSize = 20;
        List<List<Integer>> batches = new ArrayList<>();
        for (int i = 0; i < allData.size(); i += batchSize) {
            int end = Math.min(i + batchSize, allData.size());
            batches.add(allData.subList(i, end));
        }

        System.out.println("总共有 " + batches.size() + " 个批次");

        // 3. 创建线程池，最多3个线程（设置线程名方便调试）
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            private int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "模型线程-" + count++);
            }
        });

        // 4. 提交任务并收集Future结果
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < batches.size(); i++) {
            final int batchNumber = i;
            final List<Integer> batch = batches.get(i);
            futures.add(executor.submit(() -> {
                return callLargeModel(batchNumber, batch);
            }));
        }

        // 5. 等待所有任务完成
        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println(result);
            } catch (Exception e) {
                System.err.println("某个批次处理失败: " + e.getMessage());
            }
        }

        executor.shutdown();
        System.out.println("所有数据处理完成。");
    }

    // 模拟大模型处理函数，带线程信息
    private static String callLargeModel(int batchNumber, List<Integer> batch) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        int from = batch.get(0);
        int to = batch.get(batch.size() - 1);

        // 模拟调用大模型
        Thread.sleep(4000); // 模拟延迟

        return String.format("%s 处理批次 %d，数据范围：[%d - %d]", threadName, batchNumber, from, to);
    }
}
