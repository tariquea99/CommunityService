package com.technical99.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="Questions")
public class QuesLangs 
{
	@Id
	@Column(name="QuestionId")
	private Integer questionId;
	
	@Column(name="LangId")
	private Integer langId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="LangId", insertable = false, updatable= false, nullable = true)
	private Language language;
	
	
	
}
