package com.shuai.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekHotIssueDTO {
	private String hotQuestion;
	private List<DeepSeekHotItemDTO> items;
}
