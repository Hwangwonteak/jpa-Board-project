package com.hwt.hwtboard.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(length = 100)
	private String subject;
	
	@Column(length = 1000)
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)//질문이삭제되면 그질문에 달린 답변들도 모두삭제
	private List<Answer> answerList;

}
