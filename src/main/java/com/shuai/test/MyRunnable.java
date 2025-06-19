package com.shuai.test;

/**
 * @Author: KingCoding
 * @Date: 2025/6/9
 * @Description:
 */

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("原神启动...");
    }

    public static void main(String[] args) {
        MyRunnable instance = new MyRunnable();
        Thread thread = new Thread(instance);
        thread.start();
        System.out.println("启动成功");
    }
}
