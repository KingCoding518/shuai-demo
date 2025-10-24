package com.shuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shuai.domain.po.SysAddress;

import java.util.Collection;
import java.util.List;

public interface SysAddressMapper extends BaseMapper<SysAddress> {
    default List<SysAddress> selectSysAddressList() {
        return selectList(null);
    }

    default List<SysAddress> selectAddressList(Collection<String> names) {
        return selectList(Wrappers.<SysAddress>lambdaQuery()
                .in(SysAddress::getName, names));
    }
}
