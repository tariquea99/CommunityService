package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Tags")
public class Tags 
{
	@Id
	@Column(name="TagId")
	private Integer tagId;
	
	@Column(name="Title")
	private String title;
    
	@Column(name="Description")
	private String description;
	
	@Column(name="QuesCount")
	private Integer quesCount;
	
	@Column(name="CreatedDate")
	private LocalDateTime createdDate;
}
