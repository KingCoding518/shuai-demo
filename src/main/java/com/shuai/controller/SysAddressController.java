package com.shuai.controller;

import com.shuai.domain.dto.SysAddressQueryDTO;
import com.shuai.domain.vo.SysAddressVO;
import com.shuai.service.SysAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: KingCoding
 * @Date: 2025/5/20
 * @Description:
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class SysAddressController {

    private final SysAddressService sysAddressService;

    @GetMapping("/tree")
    public List<SysAddressVO> getAddressTree(SysAddressQueryDTO queryDTO) {
        return sysAddressService.getAddressTree(queryDTO);
    }

    @GetMapping("/testAscn")
    public void testAscn() {
        sysAddressService.testAscn();
    }
}
