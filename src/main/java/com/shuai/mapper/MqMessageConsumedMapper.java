package com.shuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shuai.domain.po.MqMessageConsumed;

public interface MqMessageConsumedMapper extends BaseMapper<MqMessageConsumed> {
    default void saveMqMessageConsumed(MqMessageConsumed mqMessageConsumed) {
        insert(mqMessageConsumed);
    }

    default void updateMqMessageConsumed(Integer id, int status) {
        update(Wrappers.<MqMessageConsumed>lambdaUpdate()
                .set(MqMessageConsumed::getStatus, status)
                .eq(MqMessageConsumed::getId, id));
    }

    default MqMessageConsumed selectMqMessageConsumed(String messageId) {
        return selectOne(Wrappers.<MqMessageConsumed>lambdaQuery()
                .eq(MqMessageConsumed::getMessageId, messageId));
    }
}
