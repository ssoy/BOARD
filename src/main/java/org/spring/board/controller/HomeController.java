package org.spring.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.spring.board.dto.PageDTO;
import org.spring.board.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("pdto") //세션을 생성
public class HomeController {
	
	@Autowired
	private NaverLoginService nservice;
	
	//메인폼으로 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(PageDTO pdto, Model model, HttpSession session) throws Exception {
		pdto.setCurPage(1); //초기값
		//모델을 생성해서 @SessionAttributes("pdto") 에도 생성
		model.addAttribute("pdto",pdto);
		
		//네이버 간편가입 url 얻기
		Map<String, String> resultMap = nservice.getApiUrl();
		//클라이언트 인증값 세션에 저장
		session.setAttribute("state", resultMap.get("state"));
		model.addAttribute("apiURL", resultMap.get("apiURL") );
		
		return "main";
	}
	
	//회사폼으로 이동
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() {
		return "info";
	}
}
