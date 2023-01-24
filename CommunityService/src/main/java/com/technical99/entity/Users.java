package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.technical99.enums.Community;

import lombok.Data;

@Entity
@Data
@Table(name="UserRegMaster")
public class Users 
{
	@Id
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Reputation")
	private Integer reputation;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Image")
	private String image;
	
	@Column(name="Community")
	@Enumerated(EnumType.STRING)
	private Community community;
	
	@Column(name="CommunityJoinDate")
	private LocalDateTime communityJoinDate;
	
	@Column(name="RegDate")
	private LocalDateTime regDate;
	
	@Column(name="LastPostedDate")
	private LocalDateTime lastPostedDate;
	
	@Transient
	private Integer noq;
	@Transient
	private Integer nos;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@OrderBy("CommunityVotes DESC")
//	@JoinColumn(name="UserId")
//	private UsersVotes votes;
}
