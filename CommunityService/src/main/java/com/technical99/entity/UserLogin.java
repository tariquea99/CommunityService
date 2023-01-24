package com.technical99.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name="UserLogin")
public class UserLogin
{
	@Id
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Mobile")
	private String mobile;
	
}
