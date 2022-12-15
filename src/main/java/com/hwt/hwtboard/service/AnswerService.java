package com.hwt.hwtboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hwt.hwtboard.entity.Answer;
import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.entity.SiteMember;
import com.hwt.hwtboard.repository.AnswerRepository;
import com.hwt.hwtboard.repository.MemberRepository;
import com.hwt.hwtboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final Memberservice memberservice;
	public void answerCreate(String content, Integer id,String username) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		Question question = optQuestion.get();
		
		SiteMember siteMember = memberservice.getMemberInfo(username);
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateTime(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setWriter(siteMember);
		
		answerRepository.save(answer);
		
	}
}
