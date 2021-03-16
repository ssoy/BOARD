package org.spring.board.dao;

import java.util.List;

import org.spring.board.dto.BoardFileDTO;

public interface BoardFileDAO {
	public void insert(BoardFileDTO bfdto) throws Exception;
	//파일리스트 조회
	public List<BoardFileDTO> selectList(int bnum) throws Exception;
	//파일삭제
	public void delete(int fnum);
	//게시물의 파일삭제
	public void deleteBoard(int bnum);
	
}
