package org.spring.board.service;

import java.util.Map;

public interface NaverLoginService {
	//네이버 인증 apiurl생성
	public Map<String, String> getApiUrl() throws Exception;
	
	//네이버 회원 프로필 조회
	public String getNaverUserInfo(String code, String state) throws Exception;
	
	//네이버 간편가입 로그인
	public int loginNaver(String email) throws Exception;
	
}
