package com.ssafy.happyhouse.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseMapDao;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.StoreDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {
   
   @Autowired
   SqlSession sqlSession;
   
   @Override
   public List<SidoGugunCodeDto> getSido() throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getSido();
   }

   @Override
   public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
//      return HouseMapDaoImpl.getHouseMapDao().getGugunInSido(sido);
      return sqlSession.getMapper(HouseMapDao.class).getGugunInSido(sido);
   }

   @Override
   public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getDongInGugun(gugun);
   }

   @Override
   public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getAptInDong(dong);
   }

   @Override
   public List<HouseInfoDto> getDealcontents(Map<String,String> map) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getDealcontents(map);
   }

   @Override
   public List<StoreDto> getStoreInDong(String dong) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getStoreInDong(dong);
   }

   @Override
   public List<HouseInfoDto> getDealCount() throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getDealCount();
   }

   @Override
   public void updateCntUp(HouseInfoDto houseinfoDto) throws Exception {
      sqlSession.getMapper(HouseMapDao.class).cntup(houseinfoDto);
   }

   

   @Override
   public int getCnt(Map<String,String> map) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getcnt(map);
   }
   
   @Override
   public int getAvg(String dong) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getAvg(dong);
   }

   @Override
   public double getAptAvg(Map<String,String> map) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).getAptAvg(map);
   }

   @Override
   public List<HouseInfoDto> showChart(String aptName) throws Exception {
      return sqlSession.getMapper(HouseMapDao.class).showChart(aptName);
   }

}