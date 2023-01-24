package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technical99.entity.QuesVote;

public interface QuesVoteRepository extends JpaRepository<QuesVote, Integer>
{
	@Query(value="SELECT count(q) > 0 FROM QuesVote q where q.userId=:userId And q.questionId=:quesId")
	boolean isAlreadyVoted(Integer userId, Integer quesId);
}
