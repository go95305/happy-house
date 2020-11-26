package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dao.GuestBookDao;
import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
   SqlSession sqlSession;

   @Override
   public void writeArticle(Map<String, String> map) throws Exception {
      sqlSession.getMapper(BoardDao.class).writeNotice(map);
   }

   @Override
   public List<BoardDto> listArticle(String key, String word) throws Exception {
      key = key == null ? "" : key;
      word = word == null ? "" : word;
      Map<String, String> map = new HashMap<String, String>();
      map.put("key", key);
      map.put("word", word);
      return sqlSession.getMapper(BoardDao.class).listArticle(map);
   }


   @Override
   public BoardDto getArticle(String boardno) throws Exception {
      return sqlSession.getMapper(BoardDao.class).getArticle(boardno);
   }

   @Override
   public void modifyArticle(BoardDto boardDto) throws Exception {
	   sqlSession.getMapper(BoardDao.class).modifyArticle(boardDto);
   }

   @Override
   public void deleteArticle(String articleno) throws Exception {
	   sqlSession.getMapper(BoardDao.class).deleteArticle(articleno);
   }


}