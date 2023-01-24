package com.technical99.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technical99.entity.PostNewAnswer;

public interface PostAnswerRepository extends JpaRepository<PostNewAnswer, Integer>
{
}
