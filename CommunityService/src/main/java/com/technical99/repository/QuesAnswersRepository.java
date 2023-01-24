package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.QuestionDetails;

public interface QuesAnswersRepository extends JpaRepository<QuestionDetails, Integer>
{
	public QuestionDetails findByQuestionId(Integer questionId);
	
}
