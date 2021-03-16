package org.spring.board.controller;

import org.spring.board.dto.PageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("pdto") //세션을 생성
public class HomeController {
	//메인폼으로 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(PageDTO pdto, Model model) {
		pdto.setCurPage(1);
		//모델을 생성해서 @SessionAttributes에도 생성
		model.addAttribute("pdto",pdto);
		return "main";
	}
	
	//홈으로 이동
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}
