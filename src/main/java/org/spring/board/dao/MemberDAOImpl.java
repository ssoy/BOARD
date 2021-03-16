package org.spring.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.spring.board.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public MemberDTO selectOne(String userid) {
		return session.selectOne("org.spring.board.MemberMapper.selectOne", userid);
	}

	@Override
	public int insert(MemberDTO mdto) {
		return session.insert("org.spring.board.MemberMapper.insert", mdto);
	}

	@Override
	public void emailauthUpdate(String userid) {
		session.update("org.spring.board.MemberMapper.emailauthUpdate", userid);
		
	}
	
}
