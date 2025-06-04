package com.shuai.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuai.domain.dto.*;

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

        String result = "```json\n" +
                "{\n" +
                "  \"generalCondition\": \"购房补贴咨询类诉求占比环比上升15%（占总量21%），其中补贴发放问题占该类诉求62%。\",\n" +
                "  \"hotIssueList\": [\n" +
                "    {\n" +
                "      \"hotQuestion\": \"住房保障\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"购房补贴发放问题\",\n" +
                "          \"subHotContent\": \"宿城区近一周接到3起购房补贴咨询，涉及3个小区，主要问题为购房补贴未发放及咨询发放时间。\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"退平方差咨询\",\n" +
                "          \"subHotContent\": \"宿城区近一周接到2起退平方差咨询，主要问题为养老保险发放问题及咨询生育津贴。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"hotQuestion\": \"公共设施\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"下水道堵塞问题\",\n" +
                "          \"subHotContent\": \"宿城区陈集镇王庄小区近一周接到2起下水道堵塞问题，主要问题为下水道堵塞及大便池堵塞。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"hotQuestion\": \"公共安全\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"安全隐患\",\n" +
                "          \"subHotContent\": \"宿城区丽景湾华庭近一周接到2起安全隐患问题，主要问题为小区晾衣杆存在安全隐患及物业设置柱子存在安全隐患。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"hotQuestion\": \"消费维权\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"消费纠纷\",\n" +
                "          \"subHotContent\": \"宿迁市艺品轩实木家具有限公司近一周接到2起消费纠纷，主要问题为网购衣柜退货退款纠纷及网购柜子发霉严重纠纷。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"hotQuestion\": \"劳动权益\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"社保问题\",\n" +
                "          \"subHotContent\": \"宿城区嘉尚酒业及金百年大厦7楼近一周接到2起社保问题，主要问题为公司强制扣社保钱及社保扣款纠纷。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"sensitiveIssueList\": [\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"劳动权益保障\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"大规模欠薪问题\",\n" +
                "          \"subSensitiveContent\": \"12月累计19起投诉涉及夸克能源、中南紫云集等企业，单笔最高26万元。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"消费维权\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"消费争议\",\n" +
                "          \"subSensitiveContent\": \"6起消费纠纷涉及退货被拒、强制收取服务费、提车后发现事故车等问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"公共安全\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"安全隐患\",\n" +
                "          \"subSensitiveContent\": \"3起安全隐患涉及粥铺煤气罐设置、康堡小区高空坠物及商家制作煎饼等问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"住建管理\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"房屋质量问题\",\n" +
                "          \"subSensitiveContent\": \"3起房屋质量问题涉及房屋质量争议。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"医疗健康\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"医疗争议\",\n" +
                "          \"subSensitiveContent\": \"2起医疗纠纷涉及蔡集医院上环手术未成功及药房发放错误液体问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"环境保护\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"污染治理\",\n" +
                "          \"subSensitiveContent\": \"2起环境污染问题涉及厂内排放臭味气体及砂石厂非法占地造成污染。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"公共安全\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"电梯安全问题\",\n" +
                "          \"subSensitiveContent\": \"2起电梯安全问题涉及电梯故障。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"sensitiveQuestion\": \"个人信息保护\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subSensitiveQuestion\": \"隐私泄露\",\n" +
                "          \"subSensitiveContent\": \"2起隐私泄露问题涉及个人隐私泄露导致被开除及物业泄露手机号。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"outburstIssueList\": [\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"劳动权益侵害\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"群体性欠薪事件\",\n" +
                "          \"subOutburstContent\": \"14起突发欠薪涉及建筑/服务行业，其中7万元大额欠薪持续超6个月。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"公共安全\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"停水停电\",\n" +
                "          \"subOutburstContent\": \"2起停水停电问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"安全隐患\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"安全隐患\",\n" +
                "          \"subOutburstContent\": \"2起安全隐患涉及康堡小区高空坠物及商家制作煎饼问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"电梯安全问题\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"电梯故障\",\n" +
                "          \"subOutburstContent\": \"2起电梯安全问题。\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"suggestions\": [\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"加强住房保障政策宣传\",\n" +
                "      \"suggestionContent\": \"建议住建局联合相关部门：1. 加强购房补贴政策的宣传力度；2. 优化购房补贴发放流程，确保资金及时到位。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"优化公共设施维护机制\",\n" +
                "      \"suggestionContent\": \"建议城管局联合相关部门：1. 加强下水道等公共设施的日常维护；2. 建立快速响应机制，及时处理堵塞问题。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"强化劳动权益保障\",\n" +
                "      \"suggestionContent\": \"建议人社局联合相关部门：1. 加强劳动监察，及时处理欠薪问题；2. 建立大额欠薪快速响应机制。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"加强消费维权监管\",\n" +
                "      \"suggestionContent\": \"建议市场监管局联合相关部门：1. 加强对消费纠纷的监管力度；2. 提高消费者投诉处理效率。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"提升医疗服务质量\",\n" +
                "      \"suggestionContent\": \"建议卫健委联合相关部门：1. 加强对医疗机构的监管；2. 提高医疗纠纷处理效率。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"加强环境保护执法\",\n" +
                "      \"suggestionContent\": \"建议生态环境局联合相关部门：1. 加强对环境污染问题的执法力度；2. 建立污染治理快速响应机制。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"强化个人信息保护\",\n" +
                "      \"suggestionContent\": \"建议公安局联合相关部门：1. 加强对个人信息泄露问题的监管；2. 提高隐私泄露问题的处理效率。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"加强电梯安全监管\",\n" +
                "      \"suggestionContent\": \"建议市场监管局联合相关部门：1. 加强对电梯安全的监管力度；2. 建立电梯故障快速响应机制。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"完善应急响应机制\",\n" +
                "      \"suggestionContent\": \"建议应急管理局联合相关部门：1. 完善突发事件应急响应机制；2. 提高突发事件处理效率。\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "```";
        String json = result.replaceAll("```", "").replaceAll("json", "");
        ObjectMapper mapper = new ObjectMapper();
        MonthlyReportDTO dto1 = mapper.readValue(json, MonthlyReportDTO.class);

        Map<String, Object> data = new HashMap<>();

        data.put("year", 2025);
        data.put("month", 6);
        data.put("day1", 2);
        data.put("day2", 3);
        data.put("total", 3);
        data.put("chainGrowth", 3);
        data.put("generalCondition", 3);

        data.put("hour1", "08");
        data.put("minute1", "00");
        data.put("hour2", "08");
        data.put("minute2", "00");
        data.put("publicCallNums", 123);
        data.put("validComplaintNums", 45);
        data.put("consultNums", 67);
        data.put("complainNums", 30);
        data.put("otherNums", 26);

        List<Map<String, Object>> hotIssueList = new ArrayList<>();
        for (DeepSeekHotIssueDTO hotIssue : dto1.getHotIssueList()) {
            Map<String, Object> hotIssueMap = new HashMap<>();
            hotIssueMap.put("hotQuestion", hotIssue.getHotQuestion());

            List<Map<String, Object>> items = new ArrayList<>();
            for (DeepSeekHotItemDTO item : hotIssue.getItems()) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("subHotQuestion", item.getSubHotQuestion());
                itemMap.put("subHotContent", item.getSubHotContent());
                items.add(itemMap);
            }
            hotIssueMap.put("items", items);

            hotIssueList.add(hotIssueMap);
        }
        data.put("hotIssueList", hotIssueList);

        List<Map<String, Object>> sensitiveIssueList = new ArrayList<>();
        for (DeepSeekSensitiveIssueDTO sensitiveIssue : dto1.getSensitiveIssueList()) {
            Map<String, Object> sensitiveIssueMap = new HashMap<>();
            sensitiveIssueMap.put("sensitiveQuestion", sensitiveIssue.getSensitiveQuestion());

            List<Map<String, Object>> items = new ArrayList<>();
            for (DeepSeekSensitiveItemDTO item : sensitiveIssue.getItems()) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("subSensitiveQuestion", item.getSubSensitiveQuestion());
                itemMap.put("subSensitiveContent", item.getSubSensitiveContent());
                items.add(itemMap);
            }
            sensitiveIssueMap.put("items", items);

            sensitiveIssueList.add(sensitiveIssueMap);
        }
        data.put("sensitiveIssueList", sensitiveIssueList);


        List<Map<String, Object>> outburstIssueList = new ArrayList<>();
        for (DeepSeekOutburstIssueDTO outburstIssue : dto1.getOutburstIssueList()) {
            Map<String, Object> outburstIssueMap = new HashMap<>();
            outburstIssueMap.put("outburstQuestion", outburstIssue.getOutburstQuestion());

            List<Map<String, Object>> items = new ArrayList<>();
            for (DeepSeekOutburstItemDTO item : outburstIssue.getItems()) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("subOutburstQuestion", item.getSubOutburstQuestion());
                itemMap.put("subOutburstContent", item.getSubOutburstContent());
                items.add(itemMap);
            }
            outburstIssueMap.put("items", items);

            sensitiveIssueList.add(outburstIssueMap);
        }
        data.put("sensitiveIssueList", sensitiveIssueList);

        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
        XWPFTemplate template = XWPFTemplate.compile("E:\\opt\\sucheng\\Downloads\\local\\test\\月报2.docx", builder.build()).render(data);
        template.writeToFile("E:\\opt\\sucheng\\Downloads\\local\\test\\月报111.docx");



        // // 热点诉求列表
        // List<Map<String, Object>> hotIssues = new ArrayList<>();
        // hotIssues.add(Map.of(
        //         "hotQuestion", "集中供暖问题",
        //         "hotContent", "多个小区反映家中暖气不热",
        //         "hotDepartment", "住建局"
        // ));
        // hotIssues.add(Map.of(
        //         "hotQuestion", "交通拥堵问题",
        //         "hotContent", "学校门口上下学高峰期严重拥堵",
        //         "hotDepartment", "交警部门"
        // ));
        // data.put("hotIssues", hotIssues);
        //
        // // 敏感问题列表
        // List<Map<String, Object>> burstIssues = new ArrayList<>();
        // burstIssues.add(Map.of(
        //         "burstQuestion", "自来水污染",
        //         "burstContent", "市民称自来水有异味变色",
        //         "burstDepartment", "水务局"
        // ));
        // data.put("burstIssues", burstIssues);
        //
        // // 疑难事项列表
        // List<Map<String, Object>> puzzleIssues = new ArrayList<>();
        // puzzleIssues.add(Map.of(
        //         "puzzleQuestion", "小区电梯故障",
        //         "department1", "物业公司",
        //         "content", "零件需更换",
        //         "department2", "街道办"
        // ));
        // data.put("puzzleIssues", puzzleIssues);
        // ConfigureBuilder builder = Configure.builder();
        // builder.useSpringEL();
        // // 渲染模板
        // XWPFTemplate template = XWPFTemplate.compile("F:\\test\\日报.docx", builder.build()).render(data);
        // template.writeToFile("F:\\test\\日报111.docx");
    }
}
