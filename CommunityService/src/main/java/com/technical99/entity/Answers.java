package com.technical99.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="Answers")
@Where(clause="status='Published'")
public class Answers
{
	@Id
	@Column(name="AnswerId")
	private Integer answerId;
	
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Answer")
	private String answer;
	
	@Column(name="OwnerAccept")
	private int ownerAccept;
	
	@Column(name="UpVotes")
	private Integer upVotes;
	
	@Column(name="DownVotes")
	private Integer downVotes;
	
	@JsonIgnore
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="AnswerDate")
	@OrderBy("AnswerDate")
	private LocalDateTime answerDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserId", insertable = false, updatable= false, nullable = true)
	private CommunityMember member;
	
    @OneToMany(mappedBy = "answerId", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AnswerComments> comments;
}
