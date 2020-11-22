package com.ssafy.happyhouse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dao.GuestBookDao;
import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.GuestBookService;
import com.ssafy.util.PageNavigation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	@GetMapping(value = "main/{key}/{word}")
	public List<GuestBookDto> notice(String pg, @PathVariable String key, @PathVariable String word, String spp) {
		System.out.println(key);
		List<GuestBookDto> list = null;
		int currentPage = 0;
		if (pg == null)
			currentPage = 1;
		else
			currentPage = Integer.parseInt(pg);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		try {
			list = guestbookService.listArticle(currentPage, sizePerPage, key, word);
			PageNavigation pageNavigation = guestbookService.makePageNavigation(currentPage, sizePerPage, key, word);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러");
		}
		return list;
	}

	@GetMapping(value = "mainMounted")
	public List<GuestBookDto> noticeNull( String pg, String key, String word, String spp) {
		System.out.println(key);
		List<GuestBookDto> list = null;
		int currentPage = 0;
		if (pg == null)
			currentPage = 1;
		else
			currentPage = Integer.parseInt(pg);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		try {
			list = guestbookService.listArticle(currentPage, sizePerPage, key, word);
//			PageNavigation pageNavigation = guestbookService.makePageNavigation(currentPage, sizePerPage, key, word);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러");
		}
		return list;
	}

	@GetMapping(value = "subMain")
	public List<GuestBookDto> indexNotice() {
		int currentPage = 1;
		String key = "";
		String word = "";
		int sizePerPage = 3;
		List<GuestBookDto> list = null;
		try {
			list = guestbookService.listArticle(currentPage, sizePerPage, key, word);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러");
		}
		return list;
	}

	@GetMapping("showNotice/{articleno}")
	private GuestBookDto showNotice(@PathVariable String articleno) throws Exception {
		GuestBookDto guestBookDto = null;
		try {
			guestBookDto = guestbookService.getArticle(articleno);
//			System.out.println(guestBookDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestBookDto;
	}

	@RequestMapping("noticeWrite")
	private ModelAndView noticeWrite(HttpSession session, String subject, String content, ModelAndView mv,
			GuestBookDto guestbookDto) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
			map.put("userid", memberDto.getId());
			map.put("subject", subject);
			map.put("content", content);
			guestbookService.writeArticle(map);
			mv.setViewName("redirect:/guestbook/main");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("moveModifyArticle")
	private ModelAndView moveModifyArticle(String articleno, ModelAndView mv) throws Exception {
		try {
			GuestBookDto guestBookDto = guestbookService.getArticle(articleno);
			mv.addObject("article", guestBookDto);
			mv.setViewName("happyhouse/modify");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@PutMapping("modify")
	private void modifyArticle(@RequestBody GuestBookDto guestBookDto) throws Exception {
		try {
			guestbookService.modifyArticle(guestBookDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("delete")
	private ModelAndView deleteArticle(String articleno, ModelAndView mv) throws Exception {
		try {
			guestbookService.deleteArticle(articleno);
			mv.setViewName("redirect:/guestbook/main");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@GetMapping("getTotal/{key}/{word}")
	public int getTotalCount(@PathVariable String key, @PathVariable String word) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		map.put("key", key);
		map.put("word", word);
		int count=0;
		try {
			count=guestbookService.getTotalCount(key, word);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
}