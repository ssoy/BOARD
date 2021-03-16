package org.spring.board.service;

import java.util.List;

import org.spring.board.dto.ReplyDTO;

public interface ReplyService {
	public void insert(ReplyDTO rdto) throws Exception;
	public void update(ReplyDTO rdto) throws Exception;
	public String delete(int rnum) throws Exception;
	
	public List<ReplyDTO> selectList(int bnum) throws Exception;
	
	
}
