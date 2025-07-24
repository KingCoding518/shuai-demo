package com.shuai.service;

import com.shuai.domain.po.MqMessageConsumed;

public interface MqMessageConsumedService {
    void saveMqMessageConsumed(MqMessageConsumed mqMessageConsumed);

    void updateMqMessageConsumed(Integer id, int status);

    MqMessageConsumed selectMqMessageConsumed(String messageId);
}
