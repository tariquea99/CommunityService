package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Tags")
public class QuesTags
{
	@Id
	@Column(name="TagId")
	private Integer tagId;
	
	@Column(name="Title")
	private String title;
    
}
