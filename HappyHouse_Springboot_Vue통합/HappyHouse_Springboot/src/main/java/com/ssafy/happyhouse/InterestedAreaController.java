package com.ssafy.happyhouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.InterestedAreaService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("interest")
public class InterestedAreaController {

	@Autowired
	InterestedAreaService interestSer;

	@GetMapping("showStarApi/{userid}")
	@ResponseBody
	public List<HouseInfoDto> showStarApi(@PathVariable String userid) {
		List<HouseInfoDto> list = null;

		try {
			list = interestSer.showStar(userid);
		} catch (Exception e) {
			System.out.println("에러에러");
		}

		return list;
	}

//	@RequestMapping("showStar")
//	public ModelAndView showStar(ModelAndView mv, HttpSession session) {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			String memberId = memberDto.getId();
//			try {
//				mv.addObject("starlist", interestSer.showStar(memberId));
//				mv.setViewName("/happyhouse/star_view");
//			} catch (Exception e) {
//				System.out.println("에러남?");
//			}
//		} else {
//			mv.addObject("msg", "로그인 후 사용 가능한 페이지입니다.");
//			mv.setViewName("/error");
//		}
//		return mv;
//	}

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

	@GetMapping("regist/{dongNo}/{userid}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registInterest(@PathVariable String dongNo,
			@PathVariable String userid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", userid);
		map.put("dongNo", dongNo);
		Map<String, Object> mapp = new HashMap<String, Object>();
		try {
			interestSer.registInterest(map);
			mapp.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("등록성공");

		} catch (Exception e) {
			mapp.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("등록실패");
		}

		return resEntity;
	}

	@DeleteMapping("delete/{dongNo}/{userid}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteInterest(@PathVariable String dongNo,
			@PathVariable String userid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", userid);
		map.put("dongNo", dongNo);
		Map<String, Object> mapp = new HashMap<String, Object>();
		try {
			interestSer.deleteInterest(map);
			mapp.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("삭제성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mapp.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(mapp, HttpStatus.OK);
			System.out.println("삭제실패");
		}

		return resEntity;
	}

}
