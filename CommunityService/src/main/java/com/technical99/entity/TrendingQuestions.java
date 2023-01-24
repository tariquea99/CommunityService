package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="Questions")
//@Where(clause="status='Published'")
public class TrendingQuestions
{
	@Id
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="UpVotes")
	private Integer upVotes;
	
	@Column(name="DownVotes")
	private Integer downVotes;
	
	@Column(name="Views")
	private Integer views; 
	
	@Transient
	private Boolean ownerAccept;
	
	@JsonIgnore
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="LastActivity")
	private LocalDateTime lastActivity;
	
}
