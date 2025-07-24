package com.shuai.service.impl;

import com.shuai.domain.po.MqMessageConsumed;
import com.shuai.mapper.MqMessageConsumedMapper;
import com.shuai.service.MqMessageConsumedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: KingCoding
 * @Date: 2025/7/24
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MqMessageConsumedServiceImpl implements MqMessageConsumedService {

    private final MqMessageConsumedMapper mqMessageConsumedMapper;

    @Override
    public void saveMqMessageConsumed(MqMessageConsumed mqMessageConsumed) {
        mqMessageConsumedMapper.saveMqMessageConsumed(mqMessageConsumed);
    }

    @Override
    public void updateMqMessageConsumed(Integer id, int status) {
        mqMessageConsumedMapper.updateMqMessageConsumed(id, status);
    }

    @Override
    public MqMessageConsumed selectMqMessageConsumed(String messageId) {
        return mqMessageConsumedMapper.selectMqMessageConsumed(messageId);
    }
}
