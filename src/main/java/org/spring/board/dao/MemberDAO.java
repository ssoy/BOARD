package org.spring.board.dao;

import org.spring.board.dto.MemberDTO;

public interface MemberDAO {
	public MemberDTO selectOne(String userid);
	public int insert(MemberDTO mdto);
	public void emailauthUpdate(String userid);
}
