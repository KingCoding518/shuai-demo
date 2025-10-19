package com.shuai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.shuai.domain.dto.WoScDeptDTO;
import com.shuai.domain.po.WoScDept;
import com.shuai.mapper.WoScDeptMapper;
import com.shuai.service.WoScDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: KingCoding
 * @Date: 2025/10/19
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WoScDeptServiceImpl implements WoScDeptService {

    private final WoScDeptMapper woScDeptMapper;

    @Override
    public void test(List<WoScDeptDTO> woScDeptDTOList) {

        List<String> organizerList = woScDeptDTOList.stream().map(WoScDeptDTO::getOrganizer).toList();

        List<WoScDept> woScDeptList = woScDeptMapper.getListByName(organizerList);
        if (CollUtil.isEmpty(woScDeptList) || woScDeptList.size() != organizerList.size()) {
            System.err.println("缺少承办单位");
        }
        Map<String, String> nMap = woScDeptList.stream().collect(Collectors.toMap(WoScDept::getName, WoScDept::getNameAbout));
        List<String> handleUnitList = new ArrayList<>();
        for (WoScDeptDTO woScDeptDTO : woScDeptDTOList) {
            String organizer = woScDeptDTO.getOrganizer();
            String auditOpinion = woScDeptDTO.getOption();
            String getNameAbout = nMap.getOrDefault(organizer, "");
            if (StrUtil.isNotEmpty(getNameAbout)) {
                List<String> nameList = StrUtil.split(getNameAbout, ",");
                for (String unit : nameList) {
                    Pattern compile = Pattern.compile("(沭阳|沭阳县)" + Pattern.quote(unit.replaceFirst("沭阳县|沭阳", "")));
                    Matcher matcher = compile.matcher(auditOpinion);
                    if (matcher.find()) {
                        handleUnitList.add(matcher.group(0).trim());
                        break;
                    }
                }

            }
        }
        if (handleUnitList.size() != woScDeptList.size()) {
            System.err.println("缺少处理单位或者处理意见中的一级单位与承办单位不对应");
        }

        System.err.println("匹配到的承办单位:" + handleUnitList);

    }


    public static void main(String[] args) {
        String auditOpinion = "";
        List<Pattern> patterns = new ArrayList<>();
        // for (String unit : nameList) {
        String regex = "(沭阳|沭阳县)" + Pattern.quote("住建局".replaceFirst("沭阳县|沭阳", ""));
        patterns.add(Pattern.compile(regex));
        // }
        // 兜底方案一：沭阳住房和城乡建设局工作人员队长王班长，于已联系住建局，获取“沭阳住房和城乡建设局”
        patterns.add(Pattern.compile("(沭阳|沭阳县)(?=[^，]*?(?:工作人员|民警|人员|队长|于))([^，]*?(?=工作人员|民警|人员|队长|于))"));
        // 兜底方案二：取沭阳|沭阳县后10个字符
        patterns.add(Pattern.compile("(沭阳|沭阳县)([^，]{1,10})"));
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(auditOpinion);
            if (matcher.find()) {

            }
        }
    }
}
