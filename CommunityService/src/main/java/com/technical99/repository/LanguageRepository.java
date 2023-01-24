package com.technical99.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>
{
	List<Language> findDistinctByQuestionIsNotNull();
}
