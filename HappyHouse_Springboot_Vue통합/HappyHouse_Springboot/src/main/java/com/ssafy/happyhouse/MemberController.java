package com.ssafy.happyhouse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private JwtService jwtService;

	@DeleteMapping("deleteMember/{userid}")
	private ResponseEntity<Map<String,Object>> deleteMember(@PathVariable String userid) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			memberService.deleteMember(userid);
			map.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("삭제성공");
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("삭제실패");
		}
		return resEntity;

	}

	@GetMapping("listAll")
	private @ResponseBody List<MemberDto> listAll() {
		List<MemberDto> list = null;
		try {
			list = memberService.listAll();
//			System.out.println(list.size());
		} catch (Exception e) {
		}
		return list;
	}

	@GetMapping("searchMember/{key}/")
	private @ResponseBody List<MemberDto> searchAllMember(@PathVariable String key) {
		List<MemberDto> list = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		try {
			list = memberService.searchMember(map);
//			System.out.println(list.size());
		} catch (Exception e) {
		}
		return list;
	}

	@GetMapping("searchMember/{key}/{word}")
	private @ResponseBody List<MemberDto> searchMember(@PathVariable String key, @PathVariable String word) {
		List<MemberDto> list = null;
		System.out.println(key + " : " + word);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		try {
			list = memberService.searchMember(map);
//			System.out.println(list.size());
		} catch (Exception e) {

		}
		return list;
	}

	@PutMapping("modifyMember")
	private ResponseEntity<Map<String, Object>> modifyMember(@RequestBody MemberDto memberDto) throws IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			memberService.modifyMember(memberDto);
			map.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("등록성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("등록실패");
		}
		return resEntity;
	}

	@PostMapping("signup")
	private ResponseEntity<Map<String, Object>> signup(@RequestBody MemberDto memberDto) throws IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			memberService.signup(memberDto);
			map.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("등록성공");

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println("등록실패");
		}
		return resEntity;
	}

//	@RequestMapping("logout")
//	private ModelAndView logout(HttpServletRequest request, ModelAndView mv) throws IOException {
//		HttpSession session = request.getSession();
//		session.invalidate();
//		mv.setViewName("redirect:/");
//		return mv;
//	}

	@PostMapping("login")
	private ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto mem) throws ServletException, IOException {
		Map<String, Object> map = new HashMap();
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		map.put("user", mem);

		try {
			MemberDto memberDto = memberService.login(map);
			if (memberDto != null) {
				String token = jwtService.create(memberDto);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", memberDto.getId());
				resultMap.put("user-name", memberDto.getName());
				resultMap.put("message", "로그인 성공");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.FORBIDDEN;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}
}
