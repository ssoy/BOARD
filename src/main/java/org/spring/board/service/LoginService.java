package org.spring.board.service;

import java.util.Map;

public interface LoginService {
	//로그인 체크
	public Map<String, Object> loginCheck(String userid, String passwd);
}
