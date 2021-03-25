package org.spring.board.service;

import java.util.List;

import org.spring.board.dto.ReplyDTO;

public interface ReplyService {
	public void insert(ReplyDTO rdto) throws Exception;
	public void update(ReplyDTO rdto) throws Exception;
	public String delete(int rnum) throws Exception;
	
	public List<ReplyDTO> selectList(int bnum) throws Exception;
	
	//게시물에 해당하는 댓글 삭제
	public void deleteBoard(int bnum) throws Exception;
}
