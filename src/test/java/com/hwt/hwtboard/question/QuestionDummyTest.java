package com.hwt.hwtboard.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hwt.hwtboard.service.QuestionService;

@SpringBootTest
public class QuestionDummyTest {
	@Autowired
	private QuestionService questionService;
	
	@Test
	public void testDummyData() {
		for(int i=0;i<150;i++) {
			
			String subject = String.format("테스트 데이터 입니다:%d", i);
			String content = "테스트 데이터 내용 입니다";
			questionService.questionCreate(subject, content,"aaaa");
		}
		
	}
	
}
