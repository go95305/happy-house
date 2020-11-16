package com.ssafy.happyhouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.InterestedAreaService;

@Controller
@RequestMapping("interest")
public class InterestedAreaController {

	@Autowired
	InterestedAreaService interestSer;
	
	@GetMapping("showStarApi")
	@ResponseBody
	public List<HouseInfoDto> showStarApi(HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		List<HouseInfoDto> list = null;
		if (memberDto != null) {
			String memberId = memberDto.getId();
			try {
				list = interestSer.showStar(memberId);
			} catch (Exception e) {
				System.out.println("에러남?");
			}
		} else {
		}
		return list;
	}

	@RequestMapping("showStar")
	public ModelAndView showStar(ModelAndView mv, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			String memberId = memberDto.getId();
			try {
				mv.addObject("starlist", interestSer.showStar(memberId));
				mv.setViewName("/happyhouse/star_view");
			} catch (Exception e) {
				System.out.println("에러남?");
			}
		} else {
			mv.addObject("msg", "로그인 후 사용 가능한 페이지입니다.");
			mv.setViewName("/error");
		}
		return mv;
	}

	@GetMapping("detail")
	@ResponseBody
	public List<HouseInfoDto> showDetail(String apt) {
		List<HouseInfoDto> list = null;
		try {
			list = interestSer.showDetail(apt);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	@GetMapping("regist")
	@ResponseBody
	public void registInterest(String dongNo, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		String memberId = memberDto.getId();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("dongNo", dongNo);
		try {
			interestSer.registInterest(map);
			System.out.println("등록성공");

		} catch (Exception e) {
			System.out.println("등록실패");
		}
	}

	@DeleteMapping("delete/{dongNo}")
	@ResponseBody
	public void deleteInterest(@PathVariable(value = "dongNo") String dongNo, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		String memberId = memberDto.getId();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("dongNo", dongNo);
		try {
			interestSer.deleteInterest(map);
			System.out.println("삭제 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("삭제 실패");
		}
	}

}
