package com.shuai.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekSensitiveIssueDTO {
	private String sensitiveQuestion;
	private List<DeepSeekSensitiveItemDTO> items;
}
