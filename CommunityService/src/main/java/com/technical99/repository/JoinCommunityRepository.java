package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technical99.entity.JoinCommunity;
import com.technical99.enums.Community;

public interface JoinCommunityRepository extends JpaRepository<JoinCommunity, Integer>
{
	@Query("SELECT u.community FROM JoinCommunity u where u.userId = :userId") 
	public Community findCommunityByUserId(@Param("userId") Integer userId);
}
