package com.shuai.domain.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */
@Data
public class SysAddress {

    private Long id;

    private Long code;

    private String name;

    private Integer type;

    private BigDecimal lng;

    private BigDecimal lat;

    private Long parentId;

    private String createBy;

    private LocalDateTime create_time;

    private String updateBy;

    private LocalDateTime update_time;

    private Boolean delFlag;
}
