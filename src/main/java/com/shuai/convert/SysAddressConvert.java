package com.shuai.convert;

import com.shuai.domain.po.SysAddress;
import com.shuai.domain.vo.SysAddressVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysAddressConvert {
    List<SysAddressVO> ETV(List<SysAddress> sysAddressList);
}
