package org.spring.board.service;

import java.util.List;
import java.util.Map;

import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.PageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	public Map<String, Object> selectList(PageDTO pdto) throws Exception;
	//게시물+파일
	public Map<String,Object> selectOne(int bnum) throws Exception;
	//게시물 
	public BoardDTO selectOneBoard(int bnum) throws Exception;
	
	public void insert(BoardDTO bdto,List<MultipartFile> files) throws Exception;
	
	//수정
	public void update(BoardDTO bdto,List<MultipartFile> files, List<Integer> fileDeleteList) throws Exception;
	public void delete(int bnum) throws Exception;
	//조회수+1
	public void updateReadCount(int bnum, String userid) throws Exception;
	//좋아요+1
	public void updateLikeCnt(int bnum, String userid) throws Exception;
	//싫어요+1
	public void updateDisLikeCnt(int bnum, String userid) throws Exception;
}
