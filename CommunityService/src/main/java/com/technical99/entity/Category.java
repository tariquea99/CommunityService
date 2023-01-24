package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technical99.enums.Community;

import lombok.Data;

@Entity
@Data
@Table(name="Category")
public class Category 
{
	@Id
	@Column(name="CategoryId")
	private Integer categoryId;
	
	@Column(name="Name")
	private String name;
     
	@JsonIgnore
	@Column(name="CommunityStatus")
	@Enumerated(EnumType.STRING)
	private Community status;
}
