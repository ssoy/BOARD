package org.spring.board.service;

import org.spring.board.dto.LikeUserDTO;

public interface LikeUserService {
	//type
	//1.게시물 조회
	//2.게시물 좋아요
	//3.게시물 싫어요
	//4.댓글 좋아요
	//5.댓글 싫어요
	
	//유저 추가
	public void insert(int type, int num, String userid);
	//유저 조회
	public LikeUserDTO selectOne(int type, int num, String userid);
	//유저 삭제
	public int delete(int type, int num, String userid);
	
	
	
	
	
	
	
}
