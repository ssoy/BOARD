package org.spring.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dao.BoardFileDAO;
import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.BoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private BoardFileDAO bfdao;
	
	//스프링이 만든 bean을 주입
	//root-context.xml에 bean정의
	@Autowired
	String saveDir;
	
	@Override
	public String fileUpload(MultipartFile file) {
		//파일을 폴더에 저장하고 파일명을 리턴하는 메소드
		
		if (file.isEmpty()) return ""; //파일이 없다면 리턴
		
		//유일한 번호를 생성해서 파일의 이름을 만든다
		String filename = System.currentTimeMillis() + file.getOriginalFilename();
		logger.info(filename);
		
		File f = new File(saveDir,filename);
		
		try {
			file.transferTo(f); //파일 폴더에 저장
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
	}
	
	//여러개의 파일 처리
	public List<String> fileUploadList(List<MultipartFile> files) {
		List<String> flist = new ArrayList<String>();
		for(MultipartFile file:files) {
			String filename = fileUpload(file);
			if (!filename.equals("")) flist.add(filename);
		}
		 
		return flist; //파일이름의 리스트
	}

	@Override
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception {
		//파일디렉토리에 저장
		List<String> fnamelist = fileUploadList(files);
		//파일이름db에 저장
		for(String filename:fnamelist) {
			BoardFileDTO bfdto = new BoardFileDTO();
			bfdto.setBnum(bdto.getBnum());
			bfdto.setFilename(filename);
			bfdao.insert(bfdto);
		}
		
	}

	@Override
	public void delete(List<Integer> fileDeleteList) {
		if (fileDeleteList==null) return ;
		//파일 db에서 삭제
		for(int fnum:fileDeleteList) {
			bfdao.delete(fnum);
		}
		
	}

	@Override
	public void deleteBoard(int bnum) throws Exception {
		bfdao.deleteBoard(bnum);
		
	}


}
