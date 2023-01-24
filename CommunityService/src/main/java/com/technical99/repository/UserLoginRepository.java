package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.technical99.entity.UserLogin;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String>
{
	@Query("select u.userId from UserLogin u where u.email = ?1 OR u.mobile = ?1")
	Integer getUserIdByUserName(@Param("userName") String userName);
}
