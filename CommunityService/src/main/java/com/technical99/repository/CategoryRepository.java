package com.technical99.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.Category;
import com.technical99.enums.Community;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{
	public List<Category> findByStatus(Community status);
}
