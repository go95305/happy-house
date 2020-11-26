<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<jsp:include page="../header.jsp"></jsp:include>
<div id="wrapper" style="height: 600px; width: 100%">
    <div id="wrapper-bgbtm" style="margin: 0 auto; width: 1100px; text-align: center">
        <h1>사이트 맵</h1><br><br><br>
        <img src="${root}/resources/img/sitemap.png" style="width: 90%" />
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>