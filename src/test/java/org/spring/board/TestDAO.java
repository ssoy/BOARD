package org.spring.board;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.board.dao.BoardDAO;
import org.spring.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class TestDAO {

	@Autowired
	private BoardDAO bdao;
	
	@Test
	public void testSelectList() throws Exception {
		Map<String,String> findMap = new HashMap<>();
		findMap.put("findKey", "subcon");
		findMap.put("findValue", "파일");
//		List<BoardDTO> list = bdao.selectList(findMap);
//		System.out.println(list);
	}

	@Test
	public void testSelectOne() throws Exception {
		BoardDTO bdto = bdao.selectOne(5);
		System.out.println(bdto);
	}

	@Test
	public void testInsert() throws Exception {
		BoardDTO bdto = new BoardDTO("hong", "게시물테스트3","파일게시물내용2","192.168.0.11");
		bdao.insert(bdto);
	}

	@Test
	public void testUpdate() throws Exception {
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(5);
		bdto.setSubject("수정제목");
		bdto.setContent("수정내용");
		bdao.update(bdto);
	}

	@Test
	public void testDelete() throws Exception {
		bdao.delete(5);
	}

}
