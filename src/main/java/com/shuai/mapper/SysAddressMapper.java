package com.shuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuai.domain.po.SysAddress;

import java.util.List;

public interface SysAddressMapper extends BaseMapper<SysAddress> {
    default List<SysAddress> selectSysAddressList() {
        return selectList(null);
    }
}
