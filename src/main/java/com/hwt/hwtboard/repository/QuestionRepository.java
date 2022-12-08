package com.hwt.hwtboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwt.hwtboard.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	

}
