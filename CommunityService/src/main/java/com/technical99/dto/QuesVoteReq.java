package com.technical99.dto;

import lombok.Data;

@Data
public class QuesVoteReq
{
	private Integer quesId;
	private Integer vote;
	private String type;
}
