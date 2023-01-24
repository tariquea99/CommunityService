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
@Table(name="Answers")
public class PostNewAnswer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AnswerId")
	private Integer answerId;
	
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="Answer")
	private String answer;
	
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="AnswerDate")
	private LocalDateTime answerDate;
	
	@Column(name="UserId")
	private Integer userId;
}
