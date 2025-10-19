package com.shuai.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: KingCoding
 * @Date: 2025/7/23
 * @Description:
 */

@Data
@Accessors(chain = true)
public class WoScDept {

    private Integer id;

    private String name;

    private String nameAbout;
}
