package org.spring.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.spring.board.dto.LikeUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeUserDAOImpl implements LikeUserDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(LikeUserDTO ludto) {
		session.insert("org.spring.board.LikeUserMapper.insert", ludto);
	}

	@Override
	public LikeUserDTO selectOne(LikeUserDTO ludto) {
		return session.selectOne("org.spring.board.LikeUserMapper.select", ludto);
	}

	@Override
	public int delete(LikeUserDTO ludto) {
		return session.insert("org.spring.board.LikeUserMapper.delete", ludto);
	}

}
