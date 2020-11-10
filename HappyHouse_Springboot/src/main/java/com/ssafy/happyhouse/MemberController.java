package com.ssafy.happyhouse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("deleteMember")
	private ModelAndView deleteMember(ModelAndView mv,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		try {
			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
			String id = memberDto.getId();
			memberService.deleteMember(id);
			mv.setViewName("redirect:/");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		session.invalidate();
		return mv;
		
	}
	
	@GetMapping("listAll")
	private @ResponseBody List<MemberDto> listAll() {
		List<MemberDto> list = null;
		System.out.println("모든회원검색");
		try {
			list = memberService.listAll();
			System.out.println(list.size());
		}catch (Exception e) {
		}	
		return list;
	}
	
	@GetMapping("searchMember/{key}/")
	private @ResponseBody List<MemberDto> searchAllMember(@PathVariable String key) {
		List<MemberDto> list = null;
		System.out.println("모든회원검색");
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		try {
			list = memberService.searchMember(map);
			System.out.println(list.size());
		}catch (Exception e) {
		}	
		return list;
	}
	
	@GetMapping("searchMember/{key}/{word}")
	private @ResponseBody List<MemberDto> searchMember(@PathVariable String key,@PathVariable  String word) {
		List<MemberDto> list = null;
		System.out.println("일부 회원 검색");
		System.out.println(key + " : "+ word);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		try {
			list = memberService.searchMember(map);
			System.out.println(list.size());
		}catch (Exception e) {
		}	
		return list;
	}
	
	@RequestMapping("modifyMember")
	private ModelAndView modifyMember(ModelAndView mv,MemberDto memberDto) throws IOException {
		try{
			memberService.modifyMember(memberDto);
			mv.setViewName("redirect:/");
		}catch(Exception e) {
			e.printStackTrace();
			mv.addObject("msg", "수정 중 문제가 발생했습니다.");
		}
		return mv;
	}
	
	@RequestMapping("signup")
	private ModelAndView signup(ModelAndView mv,MemberDto memberDto) throws IOException {
		try{
			memberService.signup(memberDto);
			mv.setViewName("redirect:/login");
		}catch(Exception e) {
			e.printStackTrace();
			mv.addObject("msg", "회원가입 중 문제가 발생했습니다.");
		}
		return mv;
	}

	@RequestMapping("logout")
	private ModelAndView logout(HttpServletRequest request, ModelAndView mv) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping("login")
	private ModelAndView login(ModelAndView mv,HttpServletRequest request, String id, String pw) throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		try {
			MemberDto memberDto = memberService.login(map);
			if(memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				mv.setViewName("redirect:/");
			} else {
				request.setAttribute("msg", "loginFail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
		}
		
		return mv;
		
	}
}
