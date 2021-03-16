package org.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.spring.board.dto.BoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDAOImpl implements BoardFileDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(BoardFileDTO bfdto) throws Exception {
		session.insert("org.spring.board.BoardFileMapper.insert", bfdto);
		
	}

	@Override
	public List<BoardFileDTO> selectList(int bnum) throws Exception{
		return session.selectList("org.spring.board.BoardFileMapper.selectList", bnum);
	}

	@Override
	public void delete(int fnum) {
		session.delete("org.spring.board.BoardFileMapper.delete", fnum);
		
	}

	@Override
	public void deleteBoard(int bnum) {
		session.delete("org.spring.board.BoardFileMapper.deleteBoard", bnum);
		
	}

}
