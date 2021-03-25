package org.spring.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dto.MemberDTO;
import org.spring.board.service.MemberService;
import org.spring.board.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService mservice;
	
	@Autowired
	private NaverLoginService nservice;
	
	//가입폼으로
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) throws Exception {
		//네이버 간편가입 url 얻기
		Map<String, String> resultMap = nservice.getApiUrl();
		//클라이언트 인증값 세션에 저장
		session.setAttribute("state", resultMap.get("state"));
		model.addAttribute("apiURL", resultMap.get("apiURL") );
		
		return "member/add";
	}
	
	//가입
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(MemberDTO mdto, RedirectAttributes rattr, Model model, HttpSession session) throws Exception {
		logger.info(mdto.toString());
		
		Map<String, Object> resultMap = mservice.insert(mdto);
		logger.info(resultMap.toString());
		
		//인증키를 세션에 넣기
		//key:userid, value:authKey
		session.setAttribute(mdto.getUserid(), resultMap.get("authKey"));
		session.setMaxInactiveInterval(30*60); //30분
		
		//가입실패시(-1) join으로 이동, 성공시(0) main으로 이동
		if ((int)resultMap.get("result")==0){
			//redirect 메시지 보내기
			rattr.addFlashAttribute("msg",resultMap.get("msg"));
			return "redirect:/main";
		}else {
			model.addAttribute("msg",resultMap.get("msg"));
			return "add";
		}

	}
	
	//이메일에서 인증 클릭했을때
	@RequestMapping("signUpConfirm")
	 public String signUpConfirm(@RequestParam Map<String, String> map, HttpSession session, RedirectAttributes rattr) throws Exception{
	    //userid, authKey 가 일치할경우 emailauth 업데이트
		logger.info(map.toString());
		//섹션:1)hong:200201 
		//     2)java:200202 
		String sessionAuthKey = (String) session.getAttribute(map.get("userid"));
		logger.info(sessionAuthKey); //세션에 저장된 authKey
		
		if (sessionAuthKey==null) { //인증세션이 종료되면 key사라짐
			logger.info("세션 미존재");
			return "member/add";
		}
		
		if (sessionAuthKey.equals(map.get("authKey"))){
			logger.info("메일인증성공");
			//member=>emailauth 수정
			mservice.emailauthUpdate(map.get("userid"));
			rattr.addAttribute("auth", 1);
			rattr.addFlashAttribute("msg", "인증이 완료 되었습니다");
		}else {
			rattr.addAttribute("auth", 1);
			rattr.addFlashAttribute("msg", "인증키가 일치하지 않습니다.");
		}
		//redirect는 주소 변경
		//url매핑정보
	    return "redirect:/main";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "idCheck", method = RequestMethod.POST)
	public Map<String, String> idCheck(String userid) throws Exception {
		logger.info(userid);
		Map<String, String> resultMap = mservice.idCheck(userid); 
		logger.info(resultMap.toString());
		//jackson-databind : map이나 dto를 json형태로 변환
		return resultMap;
	}
	
	//주소팝업창
	@RequestMapping(value = "jusoPopup")
	public String jusoPopup() {
		return "member/jusoPopup";
	}
	

	
	
	
	
	
	
	
	
}
