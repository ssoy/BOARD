package org.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("org.spring.board.BoardMapper.selectList", pdto);
	}

	@Override
	public BoardDTO selectOne(int bnum) throws Exception {
		return session.selectOne("org.spring.board.BoardMapper.selectOne", bnum);
	}

	@Override
	public void insert(BoardDTO bdto) throws Exception {
		session.insert("org.spring.board.BoardMapper.insert", bdto);
	}

	@Override
	public void update(BoardDTO bdto) throws Exception {
		session.update("org.spring.board.BoardMapper.update", bdto);
	}

	@Override
	public void delete(int bnum) throws Exception {
		session.delete("org.spring.board.BoardMapper.delete",bnum);
		
	}

	@Override
	public void updateReadCount(int bnum) throws Exception {
		session.update("org.spring.board.BoardMapper.updateReadCount",bnum);
	}

	@Override
	public void updateLikeCnt(int bnum) throws Exception {
		session.update("org.spring.board.BoardMapper.updateLikeCnt",bnum);
		
	}

	@Override
	public void updateDisLikeCnt(int bnum) throws Exception {
		session.update("org.spring.board.BoardMapper.updateDisLikeCnt",bnum);
		
	}

	@Override
	public void updateLikeCntMinus(int bnum) throws Exception {
		session.update("org.spring.board.BoardMapper.updateLikeCntMinus",bnum);
		
	}

	@Override
	public void updateDisLikeCntMinus(int bnum) throws Exception {
		session.update("org.spring.board.BoardMapper.updateDisLikeCntMinus",bnum);
		
	}

	@Override
	public int selectTotCnt(PageDTO pdto) {
		return session.selectOne("org.spring.board.BoardMapper.selectTotCnt", pdto);
	}
	
	
	
}
