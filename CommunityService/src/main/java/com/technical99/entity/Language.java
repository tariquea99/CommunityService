package com.technical99.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="Language")
public class Language 
{
	@Id
	@Column(name="LangId")
	private Integer langId;
	
	@Column(name="Name")
	private String name;
    
    @JsonIgnore
	@OneToMany(mappedBy = "language",cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<QuesLangs> question;
	
	
}
