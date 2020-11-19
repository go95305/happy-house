package com.ssafy.happyhouse;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.StoreDto;
import com.ssafy.happyhouse.service.HouseMapService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/storeInterest")
public class InterestedStoreController {

	@Autowired
	HouseMapService houseMapService;

	@GetMapping("sido")
	public List<SidoGugunCodeDto> getSido() {
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getSido();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("gugun")
	public List<SidoGugunCodeDto> getGugun(String sido) {
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getGugunInSido(sido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("dong")
	public List<HouseInfoDto> getDong(String gugun) {
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getDongInGugun(gugun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("store")
	public List<StoreDto> getStore(String dong) {
		List<StoreDto> list = null;
		try {
			list = houseMapService.getStoreInDong(dong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}