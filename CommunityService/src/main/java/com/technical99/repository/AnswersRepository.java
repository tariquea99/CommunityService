package com.technical99.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.technical99.entity.Answers;

@Transactional
public interface AnswersRepository extends JpaRepository<Answers, Integer>
{
	public Integer countByUserId(Integer userId);
	
	//@Query(value = "SELECT a.ownerAccept from UserQuesAnswers a where a.questionId = ?1 and a.ownerAccept = ?2", nativeQuery = true)

	@Query("select case when count(a)>0 then true else false end from Answers a where a.questionId = ?1 and a.ownerAccept = ?2")
	public Boolean getOwnerAcceptanceFlag(Integer quesId, Integer ownerAccept);
	
	public Page<Answers> findByQuestionId(Integer quesId, Pageable pageable);
	
	@Modifying
	@Query("UPDATE Answers a set a.upVotes = a.upVotes+1 WHERE a.questionId = :quesId and a.answerId = :ansId")
	public Integer increaseUpVote(Integer quesId, Integer ansId);
	
	@Modifying
	@Query("UPDATE Answers a set a.downVotes = a.downVotes+1 WHERE a.questionId = :quesId and a.answerId = :ansId")
	public Integer increaseDownVote(Integer quesId, Integer ansId);
}
