package com.hwt.hwtboard.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hwt.hwtboard.dto.Answerform;
import com.hwt.hwtboard.dto.MemberForm;
import com.hwt.hwtboard.dto.QuestionDto;
import com.hwt.hwtboard.dto.QuestionForm;
import com.hwt.hwtboard.entity.Answer;
import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.repository.AnswerRepository;
import com.hwt.hwtboard.repository.QuestionRepository;
import com.hwt.hwtboard.service.AnswerService;
import com.hwt.hwtboard.service.Memberservice;
import com.hwt.hwtboard.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private AnswerRepository answerRepository;
	
//	private final QuestionRepository questionRepository;
//	private final AnswerRepository answerRepository;
	
	private final QuestionService questionService;
	private final AnswerService answerService; 
	private final Memberservice memberService;
	
	@RequestMapping(value = "/")	
	public String home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/index")	
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")	
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		
//		List<Question> questionList = questionRepository.findAll();
		
//		List<QuestionDto> questionList = questionService.getQuestionList();
		
		Page<Question> paging = questionService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(Model model, @PathVariable("id") Integer id, Answerform answerForm) {
		
		QuestionDto question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/answerCreate/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, 
			@Valid Answerform answerForm, BindingResult bindingResult,Principal principal) {
		
		QuestionDto questionDto = questionService.getQuestion(id);
		
		principal.getName();//현재 로그인 중인 사용자의 아이디를 가져옴
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("question", questionDto);
			return "question_view";
		}
		
		answerService.answerCreate(answerForm.getContent(), id, principal.getName());
				
		return String.format("redirect:/questionView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/question_form")
	public String questionCreate(QuestionForm questionForm) {
				
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/questionCreate")
	public String questionCreateOk(@Valid QuestionForm questionForm, BindingResult bindingResult,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent(),principal.getName());
				
		return "redirect:list";
	}
	
	@RequestMapping(value = "/join")
	public String join(MemberForm memberForm) {
				
		return "join_form";
	}
	
	@PostMapping(value = "/joinOk")
	public String joinOk(@Valid MemberForm memberForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "join_form";
		}
		try {
		memberService.memberCreate(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail());
		}catch(Exception e){
			e.printStackTrace();
			bindingResult.reject("joinfail","이미 등록된 아이디 입니다.");
			return "join_form";
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
				
		return "login_form";
	}
	
}
