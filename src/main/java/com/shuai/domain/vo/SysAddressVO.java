package com.shuai.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */
@Data
public class SysAddressVO {

    private Long code;

    private String name;

    private Integer type;

    private BigDecimal lng;

    private BigDecimal lat;

    private Long parentId;

    List<SysAddressVO> children;

}
