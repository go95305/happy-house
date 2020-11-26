package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.GuestBookDao;
import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.util.PageNavigation;

@Service
public class GuestBookServiceImpl implements GuestBookService {

   @Autowired
   SqlSession sqlSession;

   @Override
   public void writeArticle(Map<String, String> map) throws Exception {
      sqlSession.getMapper(GuestBookDao.class).writeNotice(map);
   }

   @Override
   public List<GuestBookDto> listArticle(int currentPage, int sizePerPage, String key, String word) throws Exception {
      key = key == "" ? "" : key;
      word = word == "" ? "" : word;
      Map<String, String> map = new HashMap<String, String>();
      map.put("range", Integer.toString((currentPage - 1) * sizePerPage));
      map.put("sizePerPage", Integer.toString(sizePerPage));
      map.put("key", key);
      map.put("word", word);
      List<GuestBookDto> list = sqlSession.getMapper(GuestBookDao.class).listArticle(map);
//      System.out.println("hi");
//      System.out.println(list);
      return list;
   }
   @Override
   public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String key, String word)
         throws Exception {
      int naviSize = 10;
      PageNavigation pageNavigation = new PageNavigation();
      pageNavigation.setCurrentPage(currentPage);
      pageNavigation.setNaviSize(naviSize);
      int totalCount = getTotalCount(key, word);
      pageNavigation.setTotalCount(totalCount);
      int totalPageCount = (totalCount - 1) / sizePerPage + 1;
      pageNavigation.setTotalPageCount(totalPageCount);
      boolean startRange = currentPage <= naviSize;
      pageNavigation.setStartRange(startRange);
      boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
      pageNavigation.setEndRange(endRange);
      pageNavigation.makeNavigator();
      return pageNavigation;
   }

   @Override
   public GuestBookDto getArticle(String articleno) throws Exception {
      return sqlSession.getMapper(GuestBookDao.class).getArticle(articleno);
   }

   @Override
   public void modifyArticle(GuestBookDto guestBookDto) throws Exception {
	   sqlSession.getMapper(GuestBookDao.class).modifyArticle(guestBookDto);
   }

   @Override
   public void deleteArticle(String articleno) throws Exception {
	   sqlSession.getMapper(GuestBookDao.class).deleteArticle(articleno);
   }

   @Override
   public int getTotalCount(String key, String word) throws Exception {
      Map<String, String> map = new HashMap<String, String>();
      key = key == null ? "" : key;
      word = word == null ? "" : word;
      map.put("key", key);
      map.put("word", word);
      return sqlSession.getMapper(GuestBookDao.class).getTotalCount(map);
   }

@Override
public List<GuestBookDto> articleAll() throws Exception {
	return sqlSession.getMapper(GuestBookDao.class).articleAll();
}

}