package org.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatbotController {
	private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);
	@Autowired
	private ChatService chatservice;
	
	//챗봇창 폼으로 이동
	@RequestMapping(value="chatbot", method = RequestMethod.GET)
	public String add() {
		return "chatbot/chat";
	}
	
	//챗창 메시지 전달하고 답장 받기
	@RequestMapping(value="/chat/sendMsg", method = RequestMethod.POST, produces="application/text; charset=utf-8")
	public ResponseEntity<String> insert(@RequestBody String msg) throws Exception {
		String receiveMsg = chatservice.receiveMsg(msg);
		return new ResponseEntity<>(receiveMsg, HttpStatus.OK);
	}
	
}
