package com.ssafy.happyhouse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.GuestBookService;
import com.ssafy.util.PageNavigation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "main")
	public List<BoardDto> notice( String key, String word) {
		List<BoardDto> list = null;
		try {
			list = boardService.listArticle(key, word);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러");
		}
		return list;
	}

	@GetMapping("showNotice/{boardno}")
	private BoardDto showNotice(@PathVariable String boardno) throws Exception {
		BoardDto boardDto = null;
		try {
			boardDto = boardService.getArticle(boardno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardDto;
	}

	@PostMapping("noticeWrite")
	private void noticeWrite(HttpSession session,@RequestBody BoardDto boardDto) throws Exception {
		System.out.println("11111111111111111");
		System.out.println(boardDto.toString());
		
		Map<String, String> map = new HashMap<String, String>();
		try {
//			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
			map.put("userid", "ssafy");
			map.put("subject", boardDto.getSubject());
			map.put("content", boardDto.getContent());
			boardService.writeArticle(map);
			System.out.println("222222222222222");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@PutMapping("modify")
	private void modifyArticle(@RequestBody BoardDto boardDto) throws Exception {
		try {
			boardService.modifyArticle(boardDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("delete/{boardno}")
	private void deleteArticle(@PathVariable String boardno) throws Exception {
		try {
			boardService.deleteArticle(boardno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}