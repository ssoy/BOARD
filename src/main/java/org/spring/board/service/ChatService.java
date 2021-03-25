package org.spring.board.service;

public interface ChatService {
	public String receiveMsg(String voiceMessage);
	public String makeSignature(String message, String secretKey);
	public String getReqMessage(String voiceMessage);
}
