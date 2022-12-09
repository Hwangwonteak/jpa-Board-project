package com.hwt.hwtboard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.Subject;

import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.stereotype.Service;


import com.hwt.hwtboard.dto.QuestionDto;
import com.hwt.hwtboard.entity.Answer;
import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.exception.DataNotFoundException;
import com.hwt.hwtboard.repository.AnswerRepository;
import com.hwt.hwtboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public List<QuestionDto> getQuestionList() {
		List<Question> questionList = questionRepository.findAll();
		
		
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		
		
		for(int i=0;i<questionList.size();i++) {
			//System.out.println("list인덱스:"+i);
			Question question = questionList.get(i);
			QuestionDto questionDto = new QuestionDto();
			
			questionDto.setId(question.getId());
			questionDto.setContent(question.getContent());
			questionDto.setSubject(question.getSubject());
			questionDto.setAnswers(question.getAnswerList());
			questionDto.setCreateDate(question.getCreateDate());
			
			questionDtos.add(questionDto);
		}
		
		
		return questionDtos;
	}
	
	public QuestionDto getQuestion(Integer id) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		
		QuestionDto questionDto = new QuestionDto();
		if(optQuestion.isPresent()) {
			Question question = optQuestion.get();
			questionDto.setId(question.getId());
			questionDto.setContent(question.getContent());
			questionDto.setSubject(question.getSubject());
			questionDto.setAnswers(question.getAnswerList());
			questionDto.setCreateDate(question.getCreateDate());
			return questionDto;
		} else {
			throw new DataNotFoundException("해당 질문이 없습니다.");
		}		
	}
	
	public void questionCreate(String subject, String content) {
		
		
	}
	public void questionCreate( Integer id,String subject,String content) {
		Optional<Question> optQuestion	= questionRepository.findById(id);
		
		Question Question = optQuestion.get();
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		
		
		questionRepository.save(question);
	}
	
}
