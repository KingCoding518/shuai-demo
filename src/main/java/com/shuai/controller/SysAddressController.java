package com.shuai.controller;

import com.shuai.domain.dto.SysAddressQueryDTO;
import com.shuai.domain.vo.SysAddressVO;
import com.shuai.service.SysAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PostMapping("/test1")
    public void test1(@RequestBody List<String> names) {
        sysAddressService.test1(names);
    }

    @PostMapping("/test2")
    public void test1(@RequestBody Set<String> names) {
        sysAddressService.test2(names);
    }

}
