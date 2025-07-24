package com.shuai.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: KingCoding
 * @Date: 2025/7/24
 * @Description:
 */
@Data
@Accessors(chain = true)
public class MqMessageConsumed {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String messageId;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
