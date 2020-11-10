package com.ssafy.happyhouse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.GuestBookService;
import com.ssafy.util.PageNavigation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;
	
	

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView notice(String pg, String key, String word, String spp, ModelAndView mv) {
		int currentPage = 0;
	      if (pg==null)
	         currentPage = 1;
	      else
	         currentPage = Integer.parseInt(pg);
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);
		try {
			List<GuestBookDto> list = guestbookService.listArticle(currentPage, sizePerPage, key, word);
			PageNavigation pageNavigation = guestbookService.makePageNavigation(currentPage, sizePerPage, key, word);
			mv.addObject("articles", list);
			mv.addObject("navigation", pageNavigation);
			mv.setViewName("/happyhouse/notice");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러");
		}
		return mv;
	}
	
	@RequestMapping(value = "subMain", method = RequestMethod.GET)
	   public @ResponseBody List<GuestBookDto> indexNotice(ModelAndView mv) {
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
	
	@RequestMapping("showNotice")
	   private @ResponseBody GuestBookDto showNotice(String articleno) throws Exception {
	      GuestBookDto guestBookDto = null;
	      try {
	         guestBookDto = guestbookService.getArticle(articleno);
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
	
	
	@RequestMapping("modify")
	private ModelAndView modifyArticle(GuestBookDto guestBookDto, ModelAndView mv) throws Exception {
		try {
			guestbookService.modifyArticle(guestBookDto);
			mv.setViewName("redirect:/guestbook/main");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
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
}