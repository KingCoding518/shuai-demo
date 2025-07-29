package com.shuai.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuai.domain.dto.*;
import com.shuai.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: KingCoding
 * @Date: 2025/6/4
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WordServiceImpl implements WordService {


    @Override
    public void testWord() throws IOException {
        String result = "```json\n" +
                "{\n" +
                "  \"generalCondition\": \"物业类诉求占比环比上升15%（占总量21%），其中设备维修问题占该类诉求62%\",\n" +
                "  \"hotIssueList\": [\n" +
                "    {\n" +
                "      \"hotQuestion\": \"设备维护不及时\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"电梯故障维修延误\",\n" +
                "          \"subHotContent\": \"苏州悦华物业（吴中区）近一周接到4起投诉，涉及3个小区电梯超48小时未维修\"\n" +
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
                "          \"subSensitiveContent\": \"12月累计19起投诉涉及夸克能源、中南紫云集等企业，单笔最高26万元\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"outburstIssueList\": [\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"重大权益侵害\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"群体性欠薪事件\",\n" +
                "          \"subOutburstContent\": \"14起突发欠薪涉及建筑/服务行业，其中7万元大额欠薪持续超6个月\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"suggestions\": [\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"开展岁末欠薪专项整治\",\n" +
                "      \"suggestionContent\": \"建议人社局联合住建局：1.对投诉集中的建筑/物业企业实施重点审计；2.建立10万元以上的大额欠薪48小时响应机制\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "```";
        String json = result.replaceAll("```", "").replaceAll("json", "");

        // 使用Hutool JSONUtil将json字符串转为Map
       JSONObject jsonObject = JSONUtil.parseObj(json);
        Map<String, Object> data = new HashMap<>();
        // 像下面的这些魔法字符串可以抽成常量方便管理 2025/7/30 7:06 By 少帅
        data.put("year", 11);
        data.put("month", 11);
        data.put("period", 11);
        data.put("total", 11);
        data.put("chainGrowth", "23%");
        data.put("publicCallNums", 11);
        data.put("validComplaintNums", 11);
        data.put("consultNums", 11);
        data.put("complainNums", 11);
        data.put("otherNums", 11);
        data.put("generalCondition", jsonObject.getStr("generalCondition"));

        // 热点事件
        data.put("hotIssueList", jsonObject.get("hotIssueList"));
        // 敏感事件
        data.put("sensitiveIssueList", jsonObject.get("sensitiveIssueList"));
        // 突发事件
        data.put("outburstIssueList", jsonObject.get("outburstIssueList"));
        // 建议
        data.put("suggestions", jsonObject.get("suggestions"));
        // 后续可直接用data这个Map进行模板渲染等操作
        System.out.println(data);
    }

    public static void main(String[] args) throws IOException {
        String result = "```json\n" +
                "{\n" +
                "  \"generalCondition\": \"物业类诉求占比环比上升15%（占总量21%），其中设备维修问题占该类诉求62%\",\n" +
                "  \"hotIssueList\": [\n" +
                "    {\n" +
                "      \"hotQuestion\": \"设备维护不及时\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subHotQuestion\": \"电梯故障维修延误\",\n" +
                "          \"subHotContent\": \"苏州悦华物业（吴中区）近一周接到4起投诉，涉及3个小区电梯超48小时未维修\"\n" +
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
                "          \"subSensitiveContent\": \"12月累计19起投诉涉及夸克能源、中南紫云集等企业，单笔最高26万元\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"outburstIssueList\": [\n" +
                "    {\n" +
                "      \"outburstQuestion\": \"重大权益侵害\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"subOutburstQuestion\": \"群体性欠薪事件\",\n" +
                "          \"subOutburstContent\": \"14起突发欠薪涉及建筑/服务行业，其中7万元大额欠薪持续超6个月\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"suggestions\": [\n" +
                "    {\n" +
                "      \"suggestionTitle\": \"开展岁末欠薪专项整治\",\n" +
                "      \"suggestionContent\": \"建议人社局联合住建局：1.对投诉集中的建筑/物业企业实施重点审计；2.建立10万元以上的大额欠薪48小时响应机制\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "```";
        String json = result.replaceAll("```", "").replaceAll("json", "");
        ObjectMapper mapper = new ObjectMapper();
        MonthlyReportDTO resultDto = mapper.readValue(json, MonthlyReportDTO.class);


        Map<String, Object> data = new HashMap<>();
        data.put("year", 11);
        data.put("month", 11);
        data.put("period", 11);
        data.put("total", 11);
        data.put("chainGrowth", "23%");
        data.put("publicCallNums", 11);
        data.put("validComplaintNums", 11);
        data.put("consultNums", 11);
        data.put("complainNums", 11);
        data.put("otherNums", 11);
        data.put("generalCondition", resultDto.getGeneralCondition());

        // 展示热点事件
        List<Map<String, Object>> hotIssueList = new ArrayList<>();
        for (DeepSeekHotIssueDTO hotIssue : resultDto.getHotIssueList()) {
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

        // 展示敏感事件
        List<Map<String, Object>> sensitiveIssueList = new ArrayList<>();
        for (DeepSeekSensitiveIssueDTO sensitiveIssue : resultDto.getSensitiveIssueList()) {
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

        // 展示突发事件
        List<Map<String, Object>> outburstIssueList = new ArrayList<>();
        for (DeepSeekOutburstIssueDTO outburstIssue : resultDto.getOutburstIssueList()) {
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
            outburstIssueList.add(outburstIssueMap);
        }
        data.put("outburstIssueList", outburstIssueList);

        // 工作建议
        List<Map<String, Object>> suggestions = new ArrayList<>();
        for (DeepSeekSuggestionDTO suggestion : resultDto.getSuggestions()) {
            Map<String, Object> suggestionMap = new HashMap<>();
            suggestionMap.put("suggestionTitle", suggestion.getSuggestionTitle());
            suggestionMap.put("suggestionContent", suggestion.getSuggestionContent());
            suggestions.add(suggestionMap);
        }
        data.put("suggestions", suggestions);
        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
        // 渲染模板
        XWPFTemplate template = XWPFTemplate.compile("F:\\test\\月报.docx", builder.build()).render(data);
        // XWPFTemplate template = XWPFTemplate.compile("F:\\test\\日报.docx").render(data);
        template.writeToFile("F:\\test\\月报111.docx");
    }
}
