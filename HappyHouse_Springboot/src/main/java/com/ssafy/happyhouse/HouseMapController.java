package com.ssafy.happyhouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class HouseMapController {
	
	@Autowired
	HouseMapService houseMapService;
	
	
	@GetMapping("sido")
	public List<SidoGugunCodeDto> getSido(){
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getSido();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@GetMapping("gugun")
	public List<SidoGugunCodeDto> getGugun(String sido){
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getGugunInSido(sido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping("dong")
	public List<HouseInfoDto> getDong(String gugun){
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getDongInGugun(gugun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping("apt")
	public List<HouseInfoDto> getApt(String dong){
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getAptInDong(dong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@GetMapping("deal")
	public List<HouseInfoDto> getDealContents(String dong, String apt){
		List<HouseInfoDto> list = null;
		Map<String,String> map = new HashMap<String, String>();
		map.put("dong", dong);
		map.put("apt", apt);
		try {
			list = houseMapService.getDealcontents(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}