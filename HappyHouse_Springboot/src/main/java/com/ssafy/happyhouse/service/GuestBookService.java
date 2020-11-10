package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.GuestBookDto;
import com.ssafy.util.PageNavigation;

public interface GuestBookService {

   public void writeArticle(Map<String, String> map) throws Exception;
   public List<GuestBookDto> listArticle(int pg, int sizePerPage, String key, String word) throws Exception;
   public PageNavigation makePageNavigation(int pg, int sizePerPage, String key, String word) throws Exception;
   public int getTotalCount(String key, String word) throws Exception;
   
   public GuestBookDto getArticle(String articleno) throws Exception;
   public void modifyArticle(GuestBookDto guestBookDto) throws Exception;
   public void deleteArticle(String articleno) throws Exception;
   
}