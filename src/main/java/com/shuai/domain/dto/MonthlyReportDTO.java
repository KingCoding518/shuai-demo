package com.shuai.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyReportDTO {

	private String generalCondition;
	private List<DeepSeekHotIssueDTO> hotIssueList;
	private List<DeepSeekSensitiveIssueDTO> sensitiveIssueList;
	private List<DeepSeekOutburstIssueDTO> outburstIssueList;
	private List<DeepSeekSuggestionDTO> suggestions;
}
