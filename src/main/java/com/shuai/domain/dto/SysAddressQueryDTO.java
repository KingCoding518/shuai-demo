package com.shuai.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */
@Data
public class SysAddressQueryDTO {

    private Long code;

    private String name;

    private Integer type;

    private BigDecimal lng;

    private BigDecimal lat;

    private Long parentId;
}
