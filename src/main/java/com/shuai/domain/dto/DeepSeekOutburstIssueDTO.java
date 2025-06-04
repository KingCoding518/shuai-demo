package com.shuai.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeepSeekOutburstIssueDTO {
	private String outburstQuestion;
	private List<DeepSeekOutburstItemDTO> items;
}
