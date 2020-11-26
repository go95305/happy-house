package com.ssafy.happyhouse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@GetMapping("all")
	public List<GuestBookDto> noticeAll() {
		List<GuestBookDto> list = null;
		
		try {
			list = guestbookService.articleAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping(value = "mainMounted")
	public List<GuestBookDto> noticeNull( String pg, String key, String word, String spp) {
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

	@PostMapping("noticeWrite")
	private void noticeWrite(@RequestBody GuestBookDto guestbookDto) throws Exception {
		System.out.println(guestbookDto.toString());
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("userid","admin");
			map.put("subject", guestbookDto.getSubject());
			map.put("content", guestbookDto.getContent());
			guestbookService.writeArticle(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@RequestMapping("moveModifyArticle")
//	private ModelAndView moveModifyArticle(String articleno, ModelAndView mv) throws Exception {
//		try {
//			GuestBookDto guestBookDto = guestbookService.getArticle(articleno);
//			mv.addObject("article", guestBookDto);
//			mv.setViewName("happyhouse/modify");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return mv;
//	}

	@PutMapping("modify")
	private void modifyArticle(@RequestBody GuestBookDto guestBookDto) throws Exception {
		try {
			guestbookService.modifyArticle(guestBookDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("delete/{articleno}")
	private ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable String articleno) throws Exception {
		System.out.println(articleno + " :  11111111111111" );
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> mapp = new HashMap<String, Object>();
		try {
			guestbookService.deleteArticle(articleno);
			mapp.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("삭제성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
			mapp.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("삭제실패");
		}
		return resEntity;
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