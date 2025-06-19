package com.shuai.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: KingCoding
 * @Date: 2025/6/11
 * @Description:
 */

public class CounterExample {

    // private static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终计数值：" + count);
    }
}

