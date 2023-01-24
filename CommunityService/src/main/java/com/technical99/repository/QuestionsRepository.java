package com.technical99.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technical99.entity.Questions;
import com.technical99.enums.QuestionStatus;

@Transactional
public interface QuestionsRepository extends JpaRepository<Questions, Integer>
{
	public Page<Questions> findByStatusOrderByPublishDateDesc(QuestionStatus status, Pageable pageable);
	public Page<Questions> findByCategoryIdAndStatusOrderByPublishDateDesc(Integer categoryId, QuestionStatus status, Pageable pageable);
	public Page<Questions> findByLangIdAndStatusOrderByPublishDateDesc(Integer langId, QuestionStatus status, Pageable pageable);
	
	public Page<Questions> findByStatusOrderByUpVotesDesc(QuestionStatus status, Pageable pageable);
	public Page<Questions> findByCategoryIdAndStatusOrderByUpVotesDesc(Integer categoryId, QuestionStatus status, Pageable pageable);
	public Page<Questions> findByLangIdAndStatusOrderByUpVotesDesc(Integer langId, QuestionStatus status, Pageable pageable);

	public Page<Questions> findQuestionsByPublishDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
	public Page<Questions> findQuestionsByCategoryIdAndPublishDateBetween(Integer categoryId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
	public Page<Questions> findQuestionsByLangIdAndPublishDateBetween(Integer langId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

	
	public Page<Questions> findByTagsTagIdAndStatusOrderByPublishDateDesc(Integer tagId, QuestionStatus status, Pageable pageable);
	
	public Integer countByUserId(Integer userId);
	
//	Will do via trigger 
//	@Modifying
//	@Query(value = "UPDATE Questions q set q.upVotes = q.upVotes+1 WHERE q.questionId = :quesId")
//	public Integer increaseUpVote(Integer quesId);
//	
//	@Modifying
//	@Query(value = "UPDATE Questions q set q.downVotes = q.downVotes+1 WHERE q.questionId = :quesId")
//	public Integer increaseDownVote(Integer quesId);
	
}
