package com.shuai.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: KingCoding
 * @Date: 2025/7/24
 * @Description:
 */

@Data
@Accessors(chain = true)
public class Order {

    private Integer id;

    private String orderName;
}
