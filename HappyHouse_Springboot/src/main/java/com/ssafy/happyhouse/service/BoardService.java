package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {

   public void writeArticle(Map<String, String> map) throws Exception;
   public List<BoardDto> listArticle(String key, String word) throws Exception;
   
   public BoardDto getArticle(String boardno) throws Exception;
   public void modifyArticle(BoardDto boardDto) throws Exception;
   public void deleteArticle(String boardno) throws Exception;
   
}