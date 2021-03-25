package org.spring.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.board.dao.BoardDAO;
import org.spring.board.dao.BoardFileDAO;
import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.BoardFileDTO;
import org.spring.board.dto.LikeUserDTO;
import org.spring.board.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private FileService fservice;
	
	@Autowired
	private ReplyService rservice;
	
	@Autowired
	private BoardFileDAO bfdao;
	
	@Autowired
	private LikeUserService luservice;
	
	@Override
	public Map<String, Object> selectList(PageDTO pdto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		//페이징 처리 계산
		int curPage = pdto.getCurPage(); //현재페이지번호
		int perPage = pdto.getPerPage(); //한페이지당 게시물수
		int perBlock = pdto.getPerBlock();  //페이지 블럭의 수
		
		//시작번호
		//mysql은 시작번호가 0부터
		int startNo = (curPage-1)*perPage;
		//끝번호
		int endNo = startNo + perPage - 1;
		
		//시작페이지
		int startPage = curPage-((curPage-1)%perBlock);
		//끝페이지
		int endPage = startPage + perBlock-1;
		
		//전체게시물수
		int totCnt = bdao.selectTotCnt(pdto);
		//전체페이지수 
		int totPage = totCnt / perPage;
		//만약에 나머지가 있으면 +1
		if (totCnt%perPage != 0) {
			totPage ++;
		}
		//끝페이지 재수정
		if (endPage>totPage) endPage=totPage; 
		
		//pdto에 셋
		pdto.setStartNo(startNo);
		pdto.setEndNo(endNo);
		pdto.setStartPage(startPage);
		pdto.setEndPage(endPage);
		pdto.setTotPage(totPage);
		
		resultMap.put("blist", bdao.selectList(pdto)); //게시물 리스트
		resultMap.put("pdto", pdto); //페이지dto
		return resultMap;
	}
	
	//게시물+파일 리턴
	@Override
	public Map<String,Object> selectOne(int bnum) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		//게시물조회
		BoardDTO bdto = bdao.selectOne(bnum);
		//파일 조회
		List<BoardFileDTO> bflist = bfdao.selectList(bnum);
		
		resultMap.put("bdto", bdto);
		resultMap.put("bflist", bflist);
		
		return resultMap;
	}
	//게시물만 리턴
	@Override
	public BoardDTO selectOneBoard(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return bdao.selectOne(bnum);
	}

	//작업단위:commit,rollback의 수행단위
	@Transactional
	@Override
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception {
		//게시물db 저장
		bdao.insert(bdto);
		
		//게시물 파일 저장
		fservice.insert(bdto, files);
		
	}

	@Override
	public void update(BoardDTO bdto,List<MultipartFile> files, List<Integer> fileDeleteList) throws Exception {
		//게시물 수정
		bdao.update(bdto);
		
		//파일 삭제
		fservice.delete(fileDeleteList);
		
		//게시물 파일 저장
		fservice.insert(bdto, files);
		
	}

	@Transactional
	@Override
	public void delete(int bnum) throws Exception {
		//1)댓글 삭제
		rservice.deleteBoard(bnum);
		//2)게시물의 파일들 삭제
		fservice.deleteBoard(bnum);
		//3)게시물삭제
		bdao.delete(bnum);
	}

	//조회수 +1
	@Override
	public void updateReadCount(int bnum, String userid) throws Exception {
		//기존에 조회한 유저가 있는지 조회
		LikeUserDTO ludto = luservice.selectOne(1, bnum, userid);
		if (ludto!=null) return ; //유저가 있다면 return
		
		//만약에 조회 사용자가 없다면 
		//1)likeuser에 insert
		//2)조회수 +1
		//type
		//1.게시물 조회
		//2.게시물 좋아요
		//3.게시물 싫어요
		//4.댓글 좋아요
		//5.댓글 싫어요
		luservice.insert(1, bnum, userid);
		bdao.updateReadCount(bnum);//조회수 +1
		
	}
	
	@Override
	public void updateLikeCnt(int bnum, String userid) throws Exception {
		//type
		//1.게시물 조회
		//2.게시물 좋아요
		//3.게시물 싫어요
		//4.댓글 좋아요
		//5.댓글 싫어요
		
		//싫어요 유저 삭제
		int cnt = luservice.delete(3, bnum, userid);
		if (cnt >0) { //삭제가 됬다면 게시판 싫어요 -1
			bdao.updateDisLikeCntMinus(bnum);
		}
		
		//기존에 좋아요한 유저가 있는지 조회
		LikeUserDTO ludto = luservice.selectOne(2, bnum, userid);
		if (ludto!=null) return ; //유저가 있다면 return
		
		//만약에 좋아요한 사용자가 없다면 
		//1)likeuser에 insert
		luservice.insert(2, bnum, userid);
		//2)좋아요+1
		bdao.updateLikeCnt(bnum);
		
	}

	@Override
	public void updateDisLikeCnt(int bnum, String userid) throws Exception {
		//type
		//1.게시물 조회
		//2.게시물 좋아요
		//3.게시물 싫어요
		//4.댓글 좋아요
		//5.댓글 싫어요
		
		//싫어요 유저 삭제
		int cnt = luservice.delete(2, bnum, userid);
		if (cnt >0) { //삭제가 됬다면 게시판 좋아요 -1
			bdao.updateLikeCntMinus(bnum);
		}

		//기존에 싫어요한 유저가 있는지 조회
		LikeUserDTO ludto = luservice.selectOne(3, bnum, userid);
		if (ludto!=null) return ; //유저가 있다면 return
		
		//만약에 싫어요한 사용자가 없다면 
		//1)likeuser에 insert
		luservice.insert(3, bnum, userid);
		//2)싫어요+1
		bdao.updateDisLikeCnt(bnum);
		
	}


}
