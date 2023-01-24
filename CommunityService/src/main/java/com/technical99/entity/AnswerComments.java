package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="AnswerComments")
@Where(clause="status='Published'")
public class AnswerComments 
{
	@Id
	@JsonIgnore
	@Column(name="CommentId")
	private Integer commentId;
	
	@JsonIgnore
	@Column(name="AnswerId")
	private Integer answerId;
	
	@Column(name="Comment")
	private String comment;
	
	@JsonIgnore
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="CreatedDate")
	@OrderBy("createdDate DESC")
	private LocalDateTime createdDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserId", nullable = true)
	private CommunityMember user;
	
}
