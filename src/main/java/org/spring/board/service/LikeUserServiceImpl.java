package org.spring.board.service;

import org.spring.board.dao.LikeUserDAO;
import org.spring.board.dto.LikeUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeUserServiceImpl implements LikeUserService {
	
	@Autowired
	private LikeUserDAO ludao;

	@Override
	public void insert(int type, int num, String userid) {
		LikeUserDTO ludto = setLikeUserDTO(type, num, userid);
		ludao.insert(ludto);
	}

	@Override
	public LikeUserDTO selectOne(int type, int num, String userid) {
		LikeUserDTO ludto = setLikeUserDTO(type, num, userid);
		return ludao.selectOne(ludto);
	}
	
	//LikeUserDTO 세팅
	public LikeUserDTO setLikeUserDTO(int type, int num, String userid) {
		//type
		//1.게시물 조회
		//2.게시물 좋아요
		//3.게시물 싫어요
		//4.댓글 좋아요
		//5.댓글 싫어요
		LikeUserDTO ludto = new LikeUserDTO();
		if (type==1) {
			ludto.setGubun("1"); //게시물
			ludto.setNum(num); //게시물번호
			ludto.setUserid(userid); //조회한 유저id
			ludto.setLikegubun("0"); //조회
		}else if (type==2) {
			ludto.setGubun("1"); //게시물
			ludto.setNum(num); //게시물번호
			ludto.setUserid(userid); //조회한 유저id
			ludto.setLikegubun("1"); //좋아요		
		}else if (type==3) {
			ludto.setGubun("1"); //게시물
			ludto.setNum(num); //게시물번호
			ludto.setUserid(userid); //조회한 유저id
			ludto.setLikegubun("2"); //싫어요		
		}
		
		return ludto;
	}

	@Override
	public int delete(int type, int num, String userid) {
		//type
		//1.게시물 조회
		//2.게시물 좋아요
		//3.게시물 싫어요
		//4.댓글 좋아요
		//5.댓글 싫어요
		LikeUserDTO ludto = setLikeUserDTO(type, num, userid);
		
		return ludao.delete(ludto);
	}
	
	

}
