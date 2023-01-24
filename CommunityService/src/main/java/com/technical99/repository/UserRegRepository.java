package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technical99.entity.Users;

public interface UserRegRepository extends JpaRepository<Users, Integer> 
{
	@Query(value="SELECT u.name FROM UserRegMaster u where u.userId=1?", nativeQuery=true)
	public String getUserNameByUserId(Integer userId);
}
