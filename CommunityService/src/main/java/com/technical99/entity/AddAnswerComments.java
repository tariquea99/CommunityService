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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="AnswerComments")
public class AddAnswerComments
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="CommentId")
	private Integer CommentId;
	
	@Column(name="AnswerId")
	private Integer answerId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Comment")
	private String Comment;
	
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="CreatedDate")
	private LocalDateTime createdDate;
	
	@Transient
	private String userName;
}
