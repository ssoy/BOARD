package org.spring.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NaverLoginController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private NaverLoginService nservice;
	
	//네이버 url생성 폼 호출
//	@RequestMapping("naverlogin")
//	public String naverLogin(HttpSession session, Model model) throws Exception {
//		Map<String, String> resultMap = nservice.getApiUrl();
//		//클라이언트 인증값 세션에 저장
//		session.setAttribute("state", resultMap.get("state"));
//		model.addAttribute("apiURL", resultMap.get("apiURL") );
//		return "redirect:main";
//	}
	
	//네이버 로그인 인증코드 콜백
	@RequestMapping("callback")
	public String callback(String code, String state, HttpSession session) throws Exception {
		String email = nservice.getNaverUserInfo(code, state);
		
		//이메일을 이용하여 회원가입및 로그인
		nservice.loginNaver(email);
		//세션에 email저장
		session.setAttribute("userid", email);
		session.setMaxInactiveInterval(60*60*100);
		
		return "redirect:/main"; //절대경로
	}
	
	
	
}
