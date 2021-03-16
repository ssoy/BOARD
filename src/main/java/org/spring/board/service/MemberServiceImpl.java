package org.spring.board.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dao.MemberDAO;
import org.spring.board.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private FileService fservice;
	
	@Autowired
	private MailSendService mailservice;

	/* 암호화 필드 선언(DI) */
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	//아이디 체크
	@Override
	public Map<String, String> idCheck(String userid) throws Exception{
		Map<String, String> resultMap = new HashMap<>();
		MemberDTO mdto = mdao.selectOne(userid);
		if (mdto == null) { //userid가 존재하지 않는다면
			resultMap.put("msg", "사용가능한 아이디 입니다.");
			resultMap.put("yn", "y");
		}else {
			resultMap.put("msg", "아이디가 존재합니다.\n다른 아이디를 입력해 주세요!");
			resultMap.put("yn", "n");
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> insert(MemberDTO mdto) throws Exception {
		//리턴 저장변수(메세지, 성공여부(0:성공,-1:실패)
		Map<String, Object> resultMap = new HashMap<>();

		logger.info(mdto.toString());
		//기존 존재하는 회원일경우 리턴
		MemberDTO rdto = mdao.selectOne(mdto.getUserid());
		//rdto가 null 이 아니면 기존회원 존재
		if (rdto != null)  {
			logger.info("기존회원존재:" + rdto.toString());
			resultMap.put("msg", "기존회원이 존재");
			resultMap.put("result", -1); //가입실패
			return resultMap;
		}
		
		//파일처리 메소드를 결합도는 낮추고 응집도는 높이는 방법
		//1)파일처리 메소드 호출
		String filename = fservice.fileUpload(mdto.getImgfile());
		mdto.setFilename(filename);
		logger.info(mdto.toString());
		
		//2)암호화 처리(평문->암호문)
		mdto.setPasswd(bcryptEncoder.encode(mdto.getPasswd()));
		
		
		logger.info(mdto.toString());
		//추가
		int cnt = mdao.insert(mdto);
		
		//이메일 전송
		String authKey = mailservice.sendAuthMail(mdto.getEmail(),mdto.getUserid());
		logger.info(authKey);
		
		resultMap.put("authKey", authKey); //이메일인증키
		resultMap.put("msg", "가입 완료");
		resultMap.put("result", 0); //가입 성공
		
		return resultMap;
	}

	@Override
	public void emailauthUpdate(String userid) throws Exception{
		mdao.emailauthUpdate(userid);
		
	}

	
	
	
	
	
	
	
	
}
