package com.sunjinxu.controller.base;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class FormController {
	@RequestMapping("formName")
	public String loginForm(@PathVariable String formName) {
		return formName;
	}
}
