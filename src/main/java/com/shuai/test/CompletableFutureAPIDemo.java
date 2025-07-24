package com.shuai.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: KingCoding
 * @Date: 2025/6/12
 * @Description:
 */

public class CompletableFutureAPIDemo {


    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        // 不见不散
        // System.out.println(completableFuture.get());
        // 过时不候
        // System.out.println(completableFuture.get(3,TimeUnit.SECONDS));
        // 与get功能一样，但不需要抛出异常
        // System.out.println(completableFuture.join());
        // 没有计算完成的情况下给我一个替代结果
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println(completableFuture.getNow("xxx"));
        System.out.println(completableFuture.complete("completableValue") + "\t" + completableFuture.join());
    }
}
