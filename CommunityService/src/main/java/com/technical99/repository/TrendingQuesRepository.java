package com.technical99.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.technical99.entity.Questions;
import com.technical99.entity.TrendingQuestions;
import com.technical99.enums.QuestionStatus;

public interface TrendingQuesRepository extends JpaRepository<TrendingQuestions, Integer>
{
	public Page<TrendingQuestions> findAllByDownVotesLessThanEqual(Integer downVotes, Pageable pageable);
	
	//will work with this condition
	public Page<TrendingQuestions> findByDownVotesLessThanEqualAndLastActivityBetween(Integer downVotes, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
	

}
