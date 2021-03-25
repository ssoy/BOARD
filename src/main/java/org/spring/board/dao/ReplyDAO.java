package org.spring.board.dao;

import java.util.List;

import org.spring.board.dto.ReplyDTO;

public interface ReplyDAO {
	public void insert(ReplyDTO rdto) throws Exception;
	public void update(ReplyDTO rdto) throws Exception;
	public void delete(int rnum) throws Exception;
	//게시물에 해당하는 댓글 삭제
	public void deleteBoard(int bnum) throws Exception;
	
	public ReplyDTO selectOne(int rnum) throws Exception;
	
	//기존등록된 글순서 +1
	public void updateReStep(ReplyDTO rdto) throws Exception;
	
	public List<ReplyDTO> selectList(int bnum) throws Exception;
	
	public int selectReplyCnt(ReplyDTO rdto) throws Exception;
}
