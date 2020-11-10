package com.ssafy.happyhouse.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.InterestedAreaDAO;
import com.ssafy.happyhouse.model.HouseInfoDto;

@Service
public class InterestedAreaServiceImpl implements InterestedAreaService {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<HouseInfoDto> showStar(String memberId) throws Exception {
		return sqlSession.getMapper(InterestedAreaDAO.class).showStar(memberId);
	}

	@Override
	public List<HouseInfoDto> showDetail(String apt) throws Exception {
		return sqlSession.getMapper(InterestedAreaDAO.class).showDetail(apt);
	}

	@Override
	public void registInterest(Map<String,String> map) throws Exception {
		sqlSession.getMapper(InterestedAreaDAO.class).registInterest(map);
	}

	@Override
	public void deleteInterest(Map<String,String> map) throws Exception {
		sqlSession.getMapper(InterestedAreaDAO.class).deleteInterest(map);
	}

}
