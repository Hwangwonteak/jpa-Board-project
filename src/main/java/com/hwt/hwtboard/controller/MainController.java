package com.hwt.hwtboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping(value = "/index")
	@ResponseBody
	public String index() {
		return"안녕하세요";
	}
}
