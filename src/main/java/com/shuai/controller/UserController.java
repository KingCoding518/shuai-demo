package com.shuai.controller;

import com.shuai.mq.UserListener;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: KingCoding
 * @Date: 2025/7/30
 * @Description:
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserListener userListener;

    @GetMapping("/test")
    public void test() throws Exception {
        userListener.consumeOrderKnowledgeQueue();
    }
}
