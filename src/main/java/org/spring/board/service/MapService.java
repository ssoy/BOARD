package org.spring.board.service;

import java.util.Map;

public interface MapService {
	//주소를 기준으로 위치값 찾기
	public Map<String, Double> geocoding(String address) throws Exception;
}
