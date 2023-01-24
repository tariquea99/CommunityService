package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="QuesVotes")
public class QuesVote 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="QuesVoteId")
	private Integer quesVoteId;
	
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Vote")
	private Integer vote;
}