package com.hwt.hwtboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Answerform {
	@NotEmpty(message = "답변 내용은 필수 입력 사항입니다.")
	@Size(min = 10,message = "답변 내용은 10자 이상 입력하셔야 합니다.")
	private String content;
}
