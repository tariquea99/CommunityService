package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.IQPriority;
import com.technical99.enums.IQStatus;

import lombok.Data;

@Entity
@Data
@Table(name="IQMaster")
public class TrendingIQ
{
	@Id
	@Column(name="IQId")
	private Integer iqId;
	
	@Column(name="LangId")
	private Integer langId;
	
	@Column(name="TechName")
	private String techName;
	
	@Column(name="TopicName")
	private String topicName;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Question")
	private String question;
	
	@Column(name="Priority")
	@Enumerated(EnumType.STRING)
	private IQPriority priority;
	
	@Column(name="Vote")
	private Integer vote;
	
	@JsonIgnore
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private IQStatus status;
}
