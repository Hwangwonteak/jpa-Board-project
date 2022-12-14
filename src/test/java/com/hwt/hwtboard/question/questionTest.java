package com.hwt.hwtboard.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;
import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class questionTest {
	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("질문저장 테스트")
//	public void testQuestion1() {
//		Question q1 = new Question();//빈Question 객체
//		q1.setSubject("안녕하세요");
//		q1.setContent("반갑습니다");
//		q1.setCreateDate(LocalDateTime.now());
//	
//		questionRepository.save(q1);//첫번째 질문생성
//		
//		Question q2 = new Question();//빈Question 객체
//		q2.setSubject("질문있습니다");
//		q2.setContent("스프링질문");
//		q2.setCreateDate(LocalDateTime.now());
//	
//		questionRepository.save(q2);//두번째 질문생성
//	}
	@Test
	@DisplayName("조회 테스트")
	public void testQuestion2() {
		List<Question> qAll= questionRepository.findAll();
		assertEquals(2, qAll.size());
		
		Question q1 = qAll.get(0); // 첫번째글 가져오기
		assertEquals ("안녕하세요",q1.getSubject());
		
		Optional<Question> q2 = questionRepository.findById(2);//id가 2번글 가져오기
		//Optional<Question> q3 = questionRepository.findById(3);//id가 2번글 가져오기
		
		if(q2.isPresent()) {
			Question qq = q2.get();
			System.out.println("q2 내용있음");
			assertEquals ("질문있습니다",qq.getSubject());
		}
		
//		if(q3.isEmpty()) {
//			System.out.println("q3 내용없음");
//			
//		}
	}
	@Test
	@DisplayName("조회 테스트2")
	public void testQuestion3() {
		List<Question> questionList = questionRepository.findBySubject("질문있습니다");
		assertEquals ("스프링질문",questionList.get(0).getContent());
	}	
	
	@Test
	@DisplayName("조회 테스트3")
	public void testQuestion4() {
		List<Question> questionList = questionRepository.findBySubjectLike("%질문%");
		assertEquals ("스프링질문",questionList.get(0).getContent());
	}
	
	@Test
	@DisplayName("수정테스트")
	public void testQuestion5() {
		Optional<Question> q1 = questionRepository.findById(1);
		if(q1.isPresent()) {
			Question qq = q1.get();
			qq.setSubject("감사합니다");
			questionRepository.save(qq);
		}
		
	
	}	
	
	@Test
	@DisplayName("삭제 테스트")
	public void testQuestion6() {
		Optional<Question> q1 = questionRepository.findById(1);
		if(q1.isPresent()) {
			Question qq = q1.get();
			questionRepository.delete(qq);
		}
		
	
	}	
}
