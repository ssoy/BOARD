package org.spring.board.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//throws한 예외들을 처리
@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String common(Exception e, Model model) {
		System.out.println("예외발생");
		System.out.println(e.toString());
		e.printStackTrace();
		model.addAttribute("msg", e);
		return "error";
	}
}
