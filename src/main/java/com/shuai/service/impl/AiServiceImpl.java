package com.shuai.service.impl;

import com.shuai.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiServiceImpl implements AiService {

    private final OpenAiChatModel openAiChatModel;

    @Override
    public String testDialogue(String question) {
        SystemMessage systemMessage = new SystemMessage("你是一个全能助手");
        UserMessage userMessage = new UserMessage(question);
        return openAiChatModel.call(systemMessage, userMessage);
    }
}
