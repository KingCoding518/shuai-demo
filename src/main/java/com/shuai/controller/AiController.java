package com.shuai.controller;

import com.shuai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: KingCoding
 * @Date: 2025/9/1
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    @GetMapping("/testDialogue")
    public String testDialogue(String question) {
        return aiService.testDialogue(question);
    }
}
