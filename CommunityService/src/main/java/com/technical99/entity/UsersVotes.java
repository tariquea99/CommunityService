package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="UserVotes")
public class UsersVotes 
{
	@Id
	@Column(name="UserVoteId")
	private Integer userVoteId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="TutorialVotes")
	private Integer tutorialVotes;
	
	@Column(name="CodeVotes")
	private Integer codeVotes;
	
	@Column(name="BlogVotes")
	private Integer blogVotes;
	
	@Column(name="CommunityVotes")
	private Integer communityVotes;
	
}
