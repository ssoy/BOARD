package org.spring.board.service;

import java.util.List;

import org.spring.board.dao.ReplyDAO;
import org.spring.board.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDAO rdao;
	
	@Override
	public void insert(ReplyDTO rdto) throws Exception {
		//글순서 : 부모의 글순서 +1
		rdto.setRestep(rdto.getRestep()+1);
		//기존등록된 글순서 +1
		rdao.updateReStep(rdto);
		
		//글의 레벨 : 부모의 레벨 + 1
		rdto.setRelevel(rdto.getRelevel()+1);
		
		rdao.insert(rdto);
		
		
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception {
		return rdao.selectList(bnum);
	}

	@Override
	public void update(ReplyDTO rdto) throws Exception {
		rdao.update(rdto);
		
	}

	@Override
	public String delete(int rnum) throws Exception {
		//댓글의 자식이 존재한다면 삭제 불가
		ReplyDTO rdto = rdao.selectOne(rnum);
		int cnt = rdao.selectReplyCnt(rdto);
		if (cnt>0) {
			return "자식 댓글이 존재, 삭제불가!"; 
		}
		
		rdao.delete(rnum);
		return "삭제완료";
		
	}

	@Override
	public void deleteBoard(int bnum) throws Exception {
		rdao.deleteBoard(bnum);
		
	}

}
