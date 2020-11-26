<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<jsp:include page="../header.jsp"></jsp:include>
<div id="wrapper" style="height: 700px;">
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>공지사항 글수정</h2>
		<form id="writeform" method="post" action="">
		<input type="hidden" name="act" id="act" value="modify">
		<input type="hidden" name="articleno" id="articleno" value="${article.articleno}">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="subject" name="subject" value="${article.subject}">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content">${article.content}</textarea>
			</div>
			<button type="button" class="btn btn-primary" onclick="javascript:writeArticle();">글수정</button>
			<button type="reset" class="btn btn-warning">초기화</button>
		</form>
	</div>
</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
  <script type="text/javascript">
  function writeArticle() {
	if(document.getElementById("subject").value == "") {
		alert("제목 입력!!!!");
		return;
	} else if(document.getElementById("content").value == "") {
		alert("내용 입력!!!!");
		return;
	} else {
	  	document.getElementById("writeform").action = "${root}/guestbook/modify";
	  	document.getElementById("writeform").submit();
	}
  }
  </script>
</body>
</html>


