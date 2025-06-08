package com.shuai.test;

/**
 * @Author: KingCoding
 * @Date: 2025/6/7
 * @Description:
 */

public class ThreadUnsafeExample {

    public static void main(String[] args) {
        int cnt = 0;

        for (int i = 0; i < 500; i++) {
            cnt++;
        }

        for (int i = 0; i < 500; i++) {
            cnt++;
        }

        System.out.println(cnt);
    }
}
