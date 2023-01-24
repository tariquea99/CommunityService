package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technical99.entity.AnsVote;

public interface AnsVoteRepository extends JpaRepository<AnsVote, Integer>
{
	@Query(value="SELECT count(q) > 0 FROM AnsVote q where q.userId=:userId And q.questionId=:quesId And q.answerId=:ansId")
	boolean isAlreadyVoted(Integer userId, Integer quesId, Integer ansId);
}
