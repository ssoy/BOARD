package org.spring.board.service;

import java.util.HashMap;
import java.util.Map;

import org.spring.board.dao.MemberDAO;
import org.spring.board.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private MemberDAO mdao;
	
	/* 암호화 필드 선언(DI) */
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public Map<String, Object> loginCheck(String userid, String passwd) {
		//리턴 저장변수(메세지, 성공여부(0:성공,-1:실패))
		Map<String, Object> resultMap = new HashMap<>();
		
		//기존에 존재하는 회원인지 체크
		MemberDTO mdto = mdao.selectOne(userid);
		if (mdto == null) {
			//회원 미존재
			resultMap.put("msg", "아이디가 존재하지 않습니다.");
			resultMap.put("result", -1);
			return resultMap;
		}

		//기존에 존재하는 회원이면 패스워드 체크			
		//사용자가 입력한 패스워드와 기존에 db에 등록된 패스워드 매치
		//passwd평문을 암호화해서 매치여부 체크
		if (!bcryptEncoder.matches(passwd, mdto.getPasswd())) {
			//패스워드 불일치
			resultMap.put("msg", "패스워드가 일치하지 않습니다.");
			resultMap.put("result", -1);
			return resultMap;
		}
		
		//이메일 인증 체크
		if (!mdto.getEmailauth().equals("1")) {
			resultMap.put("msg", "이메일 인증을 해주세요!");
			resultMap.put("result", -1);
			return resultMap;			
		}
		
		//로그인 성공
		resultMap.put("msg", "로그인 성공");
		resultMap.put("result", 0);
		resultMap.put("thumbnail", mdto.getThumbnail());
		return resultMap;
	}
	

}
