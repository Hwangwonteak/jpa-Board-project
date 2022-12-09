package com.hwt.hwtboard.answer;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.hwt.hwtboard.entity.Answer;
import com.hwt.hwtboard.entity.Question;
import com.hwt.hwtboard.repository.AnswerRepository;
import com.hwt.hwtboard.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class answerTest {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("답변 생성 테스트")
//	
//	public void testAnswer1() {
//		Optional<Question> q2 = questionRepository.findById(2);
//		Question qq = q2.get();
//		Answer aa= new Answer();
//		
//		aa.setContent("답변입니다.");
//		aa.setCreateTime(LocalDateTime.now());
//		aa.setQuestion(qq);
//		answerRepository.save(aa);
//	}
	@Test
	@DisplayName("답변 조회 테스트")
	public void testAnswer2() {
		Optional<Answer> a1 = answerRepository.findById(3);
		
		Answer aa = a1.get();
		
		assertEquals("답변입니다.",aa.getContent());
		
	}
	@Transactional
	@Test
	@DisplayName("질문에 달린 답변 조회 테스트1")
	public void testAnswer3() {
		Optional<Question> q2 = questionRepository.findById(2);
		Question qq = q2.get();
		
		List<Answer> answerList = qq.getAnswerList();
		assertEquals(1, answerList.size());
		assertEquals("답변입니다.", answerList.get(0).getContent());
	}
	
}
