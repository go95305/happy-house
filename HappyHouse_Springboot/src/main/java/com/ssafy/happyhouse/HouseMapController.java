package com.ssafy.happyhouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<SidoGugunCodeDto> getSido() {
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getSido();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("gugun/{sido}")
	public List<SidoGugunCodeDto> getGugun(@PathVariable String sido) {
		List<SidoGugunCodeDto> list = null;
		try {
			list = houseMapService.getGugunInSido(sido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("dong/{gugun}")
	public List<HouseInfoDto> getDong(@PathVariable String gugun) {
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getDongInGugun(gugun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("apt/{dong}")
	public List<HouseInfoDto> getApt(@PathVariable String dong) {
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getAptInDong(dong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("deal/{dong}/{apt}")
	public List<HouseInfoDto> getDealContents(@PathVariable String dong, @PathVariable String apt) {
		List<HouseInfoDto> list = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("dong", dong);
		map.put("apt", apt);
		try {
			list = houseMapService.getDealcontents(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("dealCount")
	public List<HouseInfoDto> dealCount() {
		List<HouseInfoDto> list = null;
		try {
			list = houseMapService.getDealCount();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	@GetMapping("getCnt/{no}/{aptName}")
	   public int getCnt(@PathVariable String no,@PathVariable String aptName) {
	      Map<String,String> map = new HashMap<String, String>();
	      map.put("no", no);
	      map.put("aptName", aptName);
	      int cnt=0;
	      try {
	         cnt=houseMapService.getCnt(map);
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	      return cnt;
	   }

	   @PutMapping("updateCnt")
	   public void viewCnt(@RequestBody HouseInfoDto houseinfoDto) throws Exception {
	      try {
	         houseMapService.updateCntUp(houseinfoDto);
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	   }
	   
	   
	   //
	   @GetMapping("getAvg/{dong}")
	   public int getAvg(@PathVariable String dong) {
	      int avg=0;
	      try {
	         avg = houseMapService.getAvg(dong);
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	      return avg;
	   }
	   
	   @GetMapping("showChart/{aptName}")
	   public List<HouseInfoDto> showChart(@PathVariable String aptName) {
	      List<HouseInfoDto> list = null;
	      try {
	         list= houseMapService.showChart(aptName);
	         System.out.println(list.toString());
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	      return list;
	   }
	   @GetMapping("getAptAvg/{aptName}/{dong}")
	   public double getAptAvg(@PathVariable String aptName,@PathVariable String dong) {
	      System.out.println(aptName+","+dong);
	      Map<String, String> map = new HashMap<String, String>();
	      map.put("aptName", aptName);
	      map.put("dong", dong);
	      double avg=0;
	      try {
	         avg = houseMapService.getAptAvg(map);
	         System.out.println(avg);
	      } catch (Exception e) {
	         // TODO: handle exception
	      }
	      return avg;
	   }
	   
	
	

}