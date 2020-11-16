package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDto;

public interface BoardDao {

   public void writeNotice(Map<String, String> map) throws SQLException;
   public List<BoardDto> listArticle(Map<String, String> map) throws SQLException;
   
   public BoardDto getArticle(String boardno) throws SQLException;
   public void modifyArticle(BoardDto boardDto) throws SQLException;
   public void deleteArticle(String boardno) throws SQLException;
   
}