package com.hwt.hwtboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.repository.AnswerRepository;
import com.hwt.hwtboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
//	@Autowired
//	QuestionRepository questionRepository;
//	@Autowired
//	AnswerRepository answerRepository;
	
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	@RequestMapping(value = "/")
	public String home() {
		return"redirect:list";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return"redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Question> questionList=questionRepository.findAll();
		model.addAttribute("questionList",questionList);
		
		return"question_list";
	}
}
