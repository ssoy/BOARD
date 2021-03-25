package org.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.board.dto.BoardDTO;
import org.spring.board.dto.PageDTO;
import org.spring.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("board")
@SessionAttributes("pdto") //세션을 생성
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bservice;

	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add() {
		return "board/add";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add(BoardDTO bdto, List<MultipartFile> uploadfiles, HttpSession session, HttpServletRequest request) throws Exception {
		//세션에서 userid
		bdto.setUserid((String) session.getAttribute("userid")); 
		//사용자의 ip
		bdto.setIp(request.getRemoteAddr());
		bservice.insert(bdto,uploadfiles);
		
		return "redirect:/board/";
	}
	
	//조회폼으로
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(@ModelAttribute("pdto") PageDTO pdto) {
		//@ModelAttribute("pdto") =>@SessionAttributes("pdto")에 저장
		return "board/list";
	}
	
	//조회버튼 또는 페이지를 눌렀을때
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> listdata(@ModelAttribute("pdto") PageDTO pdto) throws Exception {
		return bservice.selectList(pdto);
	}
	
	//한건조회
	@RequestMapping(value = "/detail/{bnum}", method = RequestMethod.GET)
	public String detail(@PathVariable("bnum") int bnum, Model model, 
					HttpSession session, HttpServletRequest request) throws Exception {
		String userid = (String) session.getAttribute("userid");
		if (userid==null) userid = request.getRemoteAddr();

		logger.info(userid);

		//조회수+1
		bservice.updateReadCount(bnum, userid);
		
		//한건조회
		Map<String,Object> resultMap = bservice.selectOne(bnum);
		
		model.addAttribute("resultMap", resultMap);
		
		return "board/detail";
	}
	
	//좋아요 버튼
	@ResponseBody
	@RequestMapping(value = "likeCnt/{bnum}")
	public Map<String,Integer> likeCnt(@PathVariable("bnum") int bnum, HttpSession session) throws Exception {
		//좋아요 update
		bservice.updateLikeCnt(bnum, (String)session.getAttribute("userid"));
		
		Map<String, Integer> resultMap = new HashMap<>(); 
		//좋아요/싫어요 select
		BoardDTO bdto = bservice.selectOneBoard(bnum);
		resultMap.put("likecnt", bdto.getLikecnt());
		resultMap.put("dislikecnt", bdto.getDislikecnt());
		
		return resultMap;
	}
	
	//싫어요 버튼
	@ResponseBody
	@RequestMapping(value = "dislikeCnt/{bnum}")
	public Map<String,Integer> dislikeCnt(@PathVariable("bnum") int bnum, HttpSession session) throws Exception {
		//싫어요 update
		bservice.updateDisLikeCnt(bnum, (String)session.getAttribute("userid")); 
		
		Map<String, Integer> resultMap = new HashMap<>(); 
		//좋아요/싫어요 select
		BoardDTO bdto = bservice.selectOneBoard(bnum);
		resultMap.put("likecnt", bdto.getLikecnt());
		resultMap.put("dislikecnt", bdto.getDislikecnt());
		
		return resultMap;
	}
	
	
	//수정폼으로 이동
	@RequestMapping(value = "modify/{bnum}", method = RequestMethod.GET)
	public String modify(@PathVariable("bnum") int bnum, Model model) throws Exception {
		//한건조회
		model.addAttribute("resultMap", bservice.selectOne(bnum));
		
		return "board/modify";
	}
	
	//수정
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(BoardDTO bdto,
			@RequestParam(value="fileDelete", required = false) List<Integer> fileDeleteList,
			List<MultipartFile> uploadfiles) throws Exception {
		bservice.update(bdto, uploadfiles, fileDeleteList);
		
		return "redirect:/board/detail/"+bdto.getBnum();
	}
	
	//삭제
	@RequestMapping(value = "delete/{bnum}", method = RequestMethod.GET)
	public String delete(@PathVariable("bnum") int bnum, Model model) throws Exception {
		bservice.delete(bnum);
		
		return "redirect:/board/";
	}
	
	
	
	
	

}
