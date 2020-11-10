package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.GuestBookDto;

public interface GuestBookDao {

   public void writeNotice(Map<String, String> map) throws SQLException;
   public List<GuestBookDto> listArticle(Map<String, String> map) throws SQLException;
   public int getTotalCount(Map<String, String> map) throws SQLException;
   
   public GuestBookDto getArticle(String articleno) throws SQLException;
   public void modifyArticle(GuestBookDto guestBookDto) throws SQLException;
   public void deleteArticle(String articleno) throws SQLException;
   
}