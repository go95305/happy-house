<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
   href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link
   href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600|Archivo+Narrow:400,700"
   rel="stylesheet" type="text/css" />
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
   integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
   crossorigin="anonymous">
<link rel="stylesheet" href="${root}/resources/css/default.css" />
</head>

<body>
   <div id="header" class="container">
      <div id="logo">
         <h1>
            <a href="${root}/">Happy<span>House</span></a>
         </h1>
      </div>
      <div id="menu">
         <ul>
            <li class="active"><a href="${root}/" accesskey="1"
               title="">소개</a></li>
            <li><a href="#" accesskey="2"
               title="">코로나 선별소 검색</a></li>
            <c:if test="${userinfo ne null}">
            
            </c:if>
            <li><a href="${root}/sitemap" accesskey="4" title="">사이트맵</a></li>
            <c:if test="${userinfo eq null}">
               <li><a href="${root}/login" accesskey="5" title="">로그인</a></li>
            </c:if>
            <c:if test="${userinfo ne null}">
               <c:if test="${userinfo.admincode eq '1'}">
                  <li><a href="${root}/userlist" accesskey="5" title="">회원정보
                        검색</a></li>
               </c:if>
               <li><a href="${root}/management" accesskey="6"
                  title="">회원정보 수정</a></li>
               <li><a href="${root}/member/logout" accesskey="7"
                  title="">로그아웃</a></li>
            </c:if>
         </ul>
      </div>
   </div>