package org.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.spring.board.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired	
	private SqlSession session;
	
	@Override
	public void insert(ReplyDTO rdto) throws Exception {
		session.insert("org.spring.board.ReplyMapper.insert",rdto);
		
	}
	
	//기존등록된 글순서 +1
	@Override
	public void updateReStep(ReplyDTO rdto) throws Exception {
		session.update("org.spring.board.ReplyMapper.updateReStep",rdto);
		
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception{
		return session.selectList("org.spring.board.ReplyMapper.selectList",bnum);
	}

	@Override
	public void update(ReplyDTO rdto) throws Exception {
		session.update("org.spring.board.ReplyMapper.update",rdto);
	}

	@Override
	public void delete(int rnum) throws Exception {
		session.delete("org.spring.board.ReplyMapper.delete",rnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) throws Exception {
		return session.selectOne("org.spring.board.ReplyMapper.select",rnum);
	}
	//댓글의 존재여부
	@Override
	public int selectReplyCnt(ReplyDTO rdto) throws Exception {
		return session.selectOne("org.spring.board.ReplyMapper.selectReplyCnt",rdto);
	}

	@Override
	public void deleteBoard(int bnum) throws Exception {
		session.delete("org.spring.board.ReplyMapper.deleteBoard",bnum);
	}

}
