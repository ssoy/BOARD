package org.spring.board.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.spring.board.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	//파일들 저장(디렉토리+db)
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception;
	//원본파일을 폴더에 저장하고 파일명을 리턴하는 메소드
	public Map<String, Object> fileUpload(MultipartFile multifile) throws Exception;
	//썸네일 파일을 폴더에 저장하고 파일명을 리턴하는 메소드
	public String fileUpload_thumbnail(File file) throws Exception;
	
	//게시물 수정시 파일들 삭제
	public void delete(List<Integer> fileDeleteList) throws Exception;
	
	//게시물 삭제시 파일들 삭제
	public void deleteBoard(int bnum) throws Exception;
	
	
	
	
}
