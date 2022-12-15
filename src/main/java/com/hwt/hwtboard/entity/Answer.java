package com.hwt.hwtboard.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(length = 1000)
	private String content;
	
	private LocalDateTime createTime;
	
	@ManyToOne//n:1 구조 (질문1개에 답변 여러개가 달리는 구조)
	private Question question;//질문 게시판 객체 (질문게시판의 id획득)
	
	@ManyToOne
	private SiteMember writer;//글쓴이
}
