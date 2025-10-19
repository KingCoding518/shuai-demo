package com.shuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shuai.domain.po.WoScDept;

import java.util.List;

public interface WoScDeptMapper extends BaseMapper<WoScDept> {
    default List<WoScDept> getListByName(List<String> organizerList) {
        return selectList(Wrappers.<WoScDept>lambdaQuery()
                .in(WoScDept::getName, organizerList));
    }
}
