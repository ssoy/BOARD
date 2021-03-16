package org.spring.board.service;

import java.util.Map;

import org.spring.board.dto.MemberDTO;

public interface MemberService {
	public Map<String, String> idCheck(String userid) throws Exception; //아이디중복체크
	public Map<String, Object> insert(MemberDTO mdto) throws Exception;
	public void emailauthUpdate(String userid) throws Exception;
}
