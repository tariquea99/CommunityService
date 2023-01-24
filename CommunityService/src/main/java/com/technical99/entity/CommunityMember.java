package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="UserRegMaster")
public class CommunityMember 
{
	@Id
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Image")
	private String image;
	
}
