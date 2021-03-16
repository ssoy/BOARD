package org.spring.board.dao;

import org.spring.board.dto.LikeUserDTO;

public interface LikeUserDAO {
	
	public void insert(LikeUserDTO ludto); 
	
	//조회한 유저
	public LikeUserDTO selectOne(LikeUserDTO ludto);
	
	//좋아요,싫어요 유저삭제
	public int delete(LikeUserDTO ludto);
	
}
