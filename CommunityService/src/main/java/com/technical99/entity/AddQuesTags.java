package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="QuestionTagMap")
public class AddQuesTags 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="QuesTagMapId")
	private Integer quesTagMapId;
	
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="TagId")
	private Integer tagId;
    
}
