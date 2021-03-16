package org.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.PageDTO;

public interface BoardDAO {
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	public BoardDTO selectOne(int bnum) throws Exception;
	public void insert(BoardDTO bdto) throws Exception;
	public void update(BoardDTO bdto) throws Exception;
	public void delete(int bnum) throws Exception;
	//조회수+1
	public void updateReadCount(int bnum) throws Exception;
	//좋아요+1
	public void updateLikeCnt(int bnum) throws Exception;
	//싫어요+1
	public void updateDisLikeCnt(int bnum) throws Exception;
	//좋아요-1
	public void updateLikeCntMinus(int bnum) throws Exception;
	//싫어요-1
	public void updateDisLikeCntMinus(int bnum) throws Exception;	
	
	//전체 건수 조회
	public int selectTotCnt(PageDTO pdto);
	
	
	
	
	
}
