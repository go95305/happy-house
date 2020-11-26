package com.ssafy.happyhouse.service;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseInfoDto;

public interface InterestedAreaService {
	public List<HouseInfoDto> showStar(String memberId) throws Exception;
	public List<HouseInfoDto> showDetail(String apt) throws Exception;
	public void registInterest(Map<String,String> map)throws Exception;
	public void deleteInterest(Map<String,String> map) throws Exception;
}
