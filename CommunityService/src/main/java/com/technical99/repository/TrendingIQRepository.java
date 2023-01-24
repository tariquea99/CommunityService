package com.technical99.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.TrendingIQ;
import com.technical99.enums.IQStatus;

public interface TrendingIQRepository extends JpaRepository<TrendingIQ, Integer>
{
	Page<TrendingIQ> findByStatusOrderByVoteDesc(IQStatus status, Pageable pageable);
}
