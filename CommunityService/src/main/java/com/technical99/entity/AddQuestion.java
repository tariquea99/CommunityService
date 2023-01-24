package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="Questions")
public class AddQuestion 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="CategoryId")
	private Integer categoryId;
	
	@Column(name="LangId")
	private Integer langId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Question")
	private String question;
	
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="SubmittedDate")
	private LocalDateTime submittedDate;
	
	@Column(name="UserId")
	private Integer userId;
}
