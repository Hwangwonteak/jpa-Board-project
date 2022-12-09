package com.hwt.hwtboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwt.hwtboard.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	public List<Question> findBySubject(String Subject);//제목과 정확히 모두 일치하는 글 찾기
	public List<Question> findBySubjectOrderByIdDesc(String Subject);//제목과 정확히 모두 일치하는 글 찾은후 id의 내림차순으로 정렬하여 변환 
	public List<Question> findBySubjectLike(String Subject);// 제목에 특정 글자가 포함된 글 찾기

	
}
