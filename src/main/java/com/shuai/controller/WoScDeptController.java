package com.shuai.controller;

import com.shuai.domain.dto.WoScDeptDTO;
import com.shuai.service.WoScDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: KingCoding
 * @Date: 2025/10/19
 * @Description:
 */
@RestController
@RequestMapping("/woScDept")
@RequiredArgsConstructor
public class WoScDeptController {

    private final WoScDeptService woScDeptService;

    @PostMapping("/test")
    public void test(@RequestBody List<WoScDeptDTO> woScDeptDTOList) {
        woScDeptService.test(woScDeptDTOList);
    }
}
