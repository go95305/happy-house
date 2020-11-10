<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<jsp:include page="../header.jsp"></jsp:include>

<div id="wrapper">
   <form name="pageform" id="pageform" method="GET" action="">
      <input type="hidden" name="act" id="act" value="list"> <input
         type="hidden" name="pg" id="pg" value=""> <input
         type="hidden" name="key" id="key" value="${key}"> <input
         type="hidden" name="word" id="word" value="${word}">
   </form>
   <div class="container" align="center">
      <div class="col-lg-10" align="center">
         <h2>공지사항</h2>
         <c:if test="${userinfo.admincode eq '1'}">
            <table class="table table-borderless">
               <tr>
                  <td align="right"><button type="button" class="btn btn-link"
                        onclick="javascript:movewrite();">글쓰기</button></td>
               </tr>
            </table>
         </c:if>
         <form id="searchform" method="get" class="form-inline" action="">
            <input type="hidden" name="pg" id="pg" value="1">
            <table class="table table-borderless">
               <tr>
                  <td align="right"><select class="form-control" name="key"
                     id="skey">
                        <option value="userid" selected="selected">아이디</option>
                        <option value="articleno">글번호</option>
                        <option value="subject">제목</option>
                  </select> <input type="text" class="form-control" placeholder="검색어 입력."
                     name="word" id="sword">
                     <button type="button" class="btn btn-primary"
                        onclick="javascript:searchArticle();">검색</button></td>
               </tr>
            </table>
         </form>
         <c:if test="${articles.size() != 0}">
         <table class="table table-hover">
                  <thead>
                     <tr class="table-striped">
                        <th colspan="2">작성자</th>
                        <th colspan="2" >글번호</th>
                        <th colspan="2">제목</th>
                        <th colspan="2" align="center">작성일</th>
                     </tr>
                  </thead>
                  <tbody>
            <c:forEach var="article" items="${articles}">
                  
                     <tr>
                        <td colspan="2" align="left" >${article.userid}</a></td>
                        <td colspan="2" align="left" class="specificno">${article.articleno}</td>
                        <td colspan="2" align="left">${article.subject}</td>
                        <td align="left">${article.regtime}</td>
                        <c:if test="${userinfo.admincode eq '1'}">
                           <td><a
                              href="${root}/guestbook/moveModifyArticle?articleno=${article.articleno}">수정</a>
                              <a
                              href="${root}/guestbook/delete?articleno=${article.articleno}">삭제</a>
                           </td>
                        </c:if>
                     </tr>
            </c:forEach>
            </tbody>
               </table>
            <table>
               <tr>
                  <td>${navigation.navigator}</td>
               </tr>
            </table>
         </c:if>
         <c:if test="${articles.size() == 0}">
            <table class="table table-active">
               <tbody>
                  <tr class="table-info" align="center">
                     <td>작성된 글이 없습니다.</td>
                  </tr>
               </tbody>
            </table>
         </c:if>
      </div>
   </div>
<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        
        
      
      </div>
    </div>
  </div>
   <script type="text/javascript">
    $('.specificno').mousedown(function () {
       $.get("${pageContext.request.contextPath}/guestbook/showNotice",{articleno:$(this).text()},
                 function(data, status) {
                    $(".modal-content").empty();
                        let val=
                           '<div class="modal-header">'
                              + '<h4 class="modal-title">'+data.subject+'</h4>'
                              + '<button type="button" class="close" data-dismiss="modal">'+'&times;'
                           +'</div>'
                           +'<div class="modal-body">'
                           + data.content
                           +'</div>'
                           + '<div class="modal-footer">'
                           +'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
                           +'</div>'
                       $(".modal-content").append(val);
                    
                 });
       $("#myModal").modal('show');
    });
   
      function movewrite() {
         location.href = "${root}/write";
      }
      function searchArticle() {
         if (document.getElementById("sword").value == "") {
            alert("모든 목록 조회!!");
         }
         document.getElementById("searchform").action = "${root}/guestbook/main";
         document.getElementById("searchform").submit();
      }

      /* function countList() {
         alert(document.getElementById("spp").value);
      } */

      function pageMove(pg) {
         document.getElementById("pg").value = pg;
         document.getElementById("pageform").action = "${root}/guestbook/main";
         document.getElementById("pageform").submit();
      }
      
   </script>
   <jsp:include page="../footer.jsp"></jsp:include>
</div>
<!-- The Modal -->

</body>
</html>