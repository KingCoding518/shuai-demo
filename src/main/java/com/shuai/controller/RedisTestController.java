package com.shuai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/stringTest")
    public void redisStringTest() {

        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("shuai", "1");

        Object shuai = ops.get("shuai");
        System.out.println("拿到的数据:" + shuai);

        // 追加字符串
        Integer shuai1 = ops.append("shuai", "2");
        System.out.println(shuai1);
        System.out.println("追加后的数据为：" + ops.get("shuai"));

        // 报错
        // 自增操作
        // ops.increment("shuai", 1);
        // System.out.println("自增后的数据为：" + ops.get("shuai"));

        Boolean shuai2 = redisTemplate.hasKey("shuai");
        System.out.println("是否存在：" + shuai2);
    }

    @GetMapping("/hashTest")
    public void redisHashTest() {
        HashOperations ops = redisTemplate.opsForHash();

        // 存储hash值
        ops.put("shuai-demo", "shuai", "无敌最俊朗！");
        ops.put("shuai-demo", "kingCoding", "无bug");

        // 获得hash值
        Object shuai = ops.get("shuai-demo", "shuai");
        System.out.println("获取到的hash值：" + shuai);
        System.out.println("获取到的hash值：" + ops.get("shuai-demo", "kingCoding"));

        /**
         * shuai
         * kingCoding
         */
        Set keys = ops.keys("shuai-demo");
        for (Object key : keys) {
            System.out.println(key);
        }

        Map<Object, Object> entries = ops.entries("shuai-demo");
        entries.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }


    @GetMapping("/listTest")
    public void redisListTest() {

        ListOperations ops = redisTemplate.opsForList();

        // 尾部添加数据
        ops.rightPush("shuai-list", "shuai1");
        ops.rightPush("shuai-list", "shuai2");
        ops.rightPush("shuai-list", "shuai3");
        ops.rightPush("shuai-list", "shuai4");
        ops.rightPush("shuai-list", "shuai5");

        List range = ops.range("shuai-list", 0, -1);

        for (Object o : range) {
            System.out.println(o);
        }
    }


    @GetMapping("/setTest")
    public void redisSetTest() {

        SetOperations ops = redisTemplate.opsForSet();
        ops.add("shuai-set", "shuai1", "shuai2", "shuai3", "shuai4", "shuai5");

        Set members = ops.members("shuai-set");
        System.out.println("获取到集合的值："+members);


        //两个集合的差集(只属于前一集合的元素)
        ops.add("set1","1","2","3","5");
        ops.add("set2","1","2","3","4");
        Set difference = ops.difference("set1", "set2");
        System.out.println("集合1和集合2的差集"+difference);
    }

    @GetMapping("/zsetTest")
    public void redisZsetTest() {
        ZSetOperations ops = redisTemplate.opsForZSet();

        // 添加zset集合元素
        ops.add("shuai-zset", "shuai1", 1);
        ops.add("shuai-zset", "shuai5", 5);
        ops.add("shuai-zset", "shuai7", 7);
        ops.add("shuai-zset", "shuai3", 3);

        // 获取所有集合元素，默认从小到大排序
        Set range = ops.range("shuai-zset", 0, -1);
        System.out.println("获取到zset集合的所有元素：" + range);

        // 有序集合中元素的排名（从小到大）,下标默认为0开始
        Long rank = ops.rank("shuai-zset", "shuai1");
        System.out.println("获取集合的排名为：" + rank);

        Set set = ops.rangeByScore("shuai-zset", 0, 2);
        System.out.println("获取到zset集合的分数为0到2的元素：" + set);
    }
}
