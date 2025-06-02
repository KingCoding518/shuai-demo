package com.shuai.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: KingCoding
 * @Date: 2025/6/2
 * @Description:
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Object> data = new HashMap<>();

        data.put("year", 2025);
        data.put("month", 6);
        data.put("day1", 2);
        data.put("day2", 3);
        data.put("hour1", "08");
        data.put("minute1", "00");
        data.put("hour2", "08");
        data.put("minute2", "00");
        data.put("publicCallNums", 123);
        data.put("validComplaintNums", 45);
        data.put("consultNums", 67);
        data.put("complainNums", 30);
        data.put("otherNums", 26);

        // 热点诉求列表
        List<Map<String, Object>> hotIssues = new ArrayList<>();
        hotIssues.add(Map.of(
                "hotQuestion", "集中供暖问题",
                "hotContent", "多个小区反映家中暖气不热",
                "hotDepartment", "住建局"
        ));
        hotIssues.add(Map.of(
                "hotQuestion", "交通拥堵问题",
                "hotContent", "学校门口上下学高峰期严重拥堵",
                "hotDepartment", "交警部门"
        ));
        data.put("hotIssues", hotIssues);

        // 敏感问题列表
        List<Map<String, Object>> burstIssues = new ArrayList<>();
        burstIssues.add(Map.of(
                "burstQuestion", "自来水污染",
                "burstContent", "市民称自来水有异味变色",
                "burstDepartment", "水务局"
        ));
        data.put("burstIssues", burstIssues);

        // 疑难事项列表
        List<Map<String, Object>> puzzleIssues = new ArrayList<>();
        puzzleIssues.add(Map.of(
                "puzzleQuestion", "小区电梯故障",
                "department1", "物业公司",
                "content", "零件需更换",
                "department2", "街道办"
        ));
        data.put("puzzleIssues", puzzleIssues);
        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
        // 渲染模板
        XWPFTemplate template = XWPFTemplate.compile("F:\\test\\日报.docx", builder.build()).render(data);
        template.writeToFile("F:\\test\\日报111.docx");
    }
}
