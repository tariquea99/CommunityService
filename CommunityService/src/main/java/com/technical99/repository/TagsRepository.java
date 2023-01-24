package com.technical99.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.Tags;

public interface TagsRepository extends JpaRepository<Tags, Integer>
{
	public Page<Tags> findByTitleContaining(String title, Pageable pageable);
	
	//public List<Tags> findAll(Sort.by(Sort.Direction.DESC, "quesCount"));
	
}
