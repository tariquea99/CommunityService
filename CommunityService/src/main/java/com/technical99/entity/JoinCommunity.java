package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.technical99.enums.Community;

import lombok.Data;

@Entity
@Data
@Table(name="UserRegMaster")
public class JoinCommunity 
{
	@Id
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Community")
	@Enumerated(EnumType.STRING)
	private Community community;
	
	@Column(name="CommunityJoinDate")
	private LocalDateTime communityJoinDate;
	
}
