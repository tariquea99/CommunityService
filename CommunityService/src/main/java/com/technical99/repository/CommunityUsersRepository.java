package com.technical99.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.Users;
import com.technical99.enums.Community;

public interface CommunityUsersRepository extends JpaRepository<Users, Integer>
{
	public Page<Users> findByCommunity(Community status, Pageable pageable); 
	
	public Page<Users> findByNameContaining(String name, Pageable pageable);
	
	/*public Page<Users> findByCommunityOrderByCommunityJoinDateDesc(Community status, Pageable pageable); 
	
	public Page<Users> findByCommunityOrderByLastPostedDateDesc(Community status, Pageable pageable); 
}*/
}
