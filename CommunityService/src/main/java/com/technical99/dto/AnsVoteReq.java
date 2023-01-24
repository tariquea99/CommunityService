package com.technical99.dto;

import lombok.Data;

@Data
public class AnsVoteReq
{
	private Integer ansId;
	private Integer quesId;
	private Integer vote;
}
