package com.shuai.service;

import com.shuai.domain.dto.SysAddressQueryDTO;
import com.shuai.domain.vo.SysAddressVO;

import java.util.List;

public interface SysAddressService {
    List<SysAddressVO> getAddressTree(SysAddressQueryDTO queryDTO);

    void testAscn();
}
