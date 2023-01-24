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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.QuestionStatus;

import lombok.Data;

@Entity
@Data
@Table(name="Questions")
public class Questions 
{
	@Id
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="CategoryId")
	private Integer categoryId;
	
	@Column(name="LangId")
	private Integer langId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Question")
	private String question;
	
	@Column(name="UpVotes")
	private Integer upVotes;
	
	@Column(name="DownVotes")
	private Integer downVotes;
	
	@Column(name="Views")
	private Integer views;
	
	@Column(name="NoOfAns")
	private Integer noOfAns;
	
	@JsonIgnore
	@Column(name="Status")
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@Column(name="PublishDate")
	private LocalDateTime publishDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId", insertable = false,updatable = false, nullable = true)
	private CommunityMember member;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="QuestionTagMap", joinColumns=@JoinColumn(name="questionId"), inverseJoinColumns=@JoinColumn(name="tagId"))
	private List<QuesTags> tags;
	
}
