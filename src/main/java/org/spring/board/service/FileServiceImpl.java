package org.spring.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dao.BoardFileDAO;
import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.BoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileServiceImpl implements FileService{

	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private BoardFileDAO bfdao;
	
	//스프링이 만든 bean을 주입
	//root-context.xml에 bean정의
	@Autowired
	String saveDir;
	

	//게시물의 여러개의 파일 처리
	public List<Map<String, String>> fileUploadList(List<MultipartFile> files) throws Exception {
		//파일맵의 리스트
		List<Map<String, String>> fnamelist = new ArrayList<>();
		//원본파일명 +썸네일 파일명 맵
		Map<String, String> filenameMap = new HashMap<>(); 
		
		for(MultipartFile multifile:files) {
			//1)원본 파일처리 
			Map<String, Object> resultMap = fileUpload(multifile);
			if (resultMap == null)  continue;
			String filename = (String) resultMap.get("filename");
			//2)썸네일 파일 처리
			File file = (File) resultMap.get("file");			
			String thumbnail = fileUpload_thumbnail(file);
			
			filenameMap.put("filename", filename);
			filenameMap.put("thumbnail", thumbnail);
			fnamelist.add(filenameMap);
		}
		 
		return fnamelist; //파일이름의 리스트
	}

	//원본파일 저장
	@Override
	public Map<String, Object> fileUpload(MultipartFile multifile) throws Exception{
		//파일을 폴더에 저장하고 파일명과 파일을 리턴하는 메소드
		
		Map<String, Object> resultMap = null;
		
		if (multifile.isEmpty()) return null; //파일이 없다면 리턴
		
		//유일한 번호를 생성해서 파일의 이름을 만든다
		String filename = System.currentTimeMillis() + multifile.getOriginalFilename();
		LOGGER.info(filename);
		
		File file = new File(saveDir,filename);
		System.out.println("원본파일:" + file);
		multifile.transferTo(file); //파일 폴더에 저장
		resultMap = new HashMap<>();
		resultMap.put("filename", filename);
		resultMap.put("file", file);
		
		return resultMap;
	}
	
	//썸네일 저장
	@Override
	public String fileUpload_thumbnail(File file) throws Exception{
		//파일을 폴더에 저장하고 파일명을 리턴하는 메소드
		
		if (!file.exists()) return null; //파일이 없다면 리턴
		
		//유일한 번호를 생성해서 파일의 이름을 만든다
		//s_ : 썸네일파일
		String filename = "s_" + file.getName();
		LOGGER.info(filename);
		
		File f = new File(saveDir,filename);
		
		f.getParentFile().mkdirs();
		Thumbnails.of(file).size(300, 300).toFile(f);//파일저장
		
		return filename;
	}

	@Override
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception {
		//파일디렉토리에 저장
		List<Map<String,String>> fnamelist = fileUploadList(files);
		//파일이름db에 저장
		for(Map<String,String> fmap:fnamelist) {
			BoardFileDTO bfdto = new BoardFileDTO();
			bfdto.setBnum(bdto.getBnum());
			bfdto.setFilename(fmap.get("filename"));
			bfdto.setThumbnail(fmap.get("thumbnail"));
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
