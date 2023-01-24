package com.technical99.dto;

import java.util.List;

import lombok.Data;

@Data
public class AddQues 
{
	private Integer categoryId;
	private Integer langId;
	private String title;
	private String question;
	private List<Integer> tagIds; 
}
