package com.shuai.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: KingCoding
 * @Date: 2025/6/10
 * @Description:
 */

public class testArray {

    public static void main(String[] args) {
        synchronized (testArray.class) {

        }

        List<Integer> list = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < 120; i++) {
            list.add(num++);
        }

        List<Integer> integers = list.subList(0, 10);
        System.out.println(integers);
    }
}
