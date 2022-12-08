package com.hwt.hwtboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwt.hwtboard.entity.Answer;
import com.hwt.hwtboard.entity.Question;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{ 

}
