package com.shuai.service;

import com.shuai.domain.dto.SysAddressQueryDTO;
import com.shuai.domain.vo.SysAddressVO;

import java.util.List;
import java.util.Set;

public interface SysAddressService {
    List<SysAddressVO> getAddressTree(SysAddressQueryDTO queryDTO);

    void testAscn();

    void test1(List<String> names);

    void test2(Set<String> names);
}
