package org.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dto.ReplyDTO;
import org.spring.board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//각메소드의 @ResponseBody 생략
@RestController
@RequestMapping("reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService rservice;
	//댓글 추가
	@RequestMapping(value="/", method = RequestMethod.POST )
	public String add(@RequestBody ReplyDTO rdto, HttpServletRequest request, HttpSession session) throws Exception {
		rdto.setUserid((String)session.getAttribute("userid"));
		rdto.setIp(request.getRemoteAddr()); //접속ip
		rservice.insert(rdto);
		
		return "success!";
	}
	
	//댓글 수정
	@RequestMapping(value="/", method = RequestMethod.PUT )
	public String modify(@RequestBody ReplyDTO rdto) throws Exception {
		rservice.update(rdto);
		
		return "success!";
	}

	//댓글 삭제
	@RequestMapping(value="/{rnum}", method = RequestMethod.DELETE,produces = "application/text;charset=utf-8")
	public String remove(@PathVariable("rnum") int rnum) throws Exception {
		
		return rservice.delete(rnum);
	}
	
	//댓글 리스트
	@RequestMapping(value="/{bnum}", method = RequestMethod.GET )
	public List<ReplyDTO> list(@PathVariable("bnum") int bnum) throws Exception {
		
		return rservice.selectList(bnum);
	}
	
	
}
