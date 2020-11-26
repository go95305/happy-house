<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<jsp:include page="../header.jsp"></jsp:include>
<div id="wrapper" style="height: 600px;">

	<div class="searchcontainer" align="center">
		<form id="searchform" method="post" class="form-inline"
			action="${root}/member/searchMember">
			<table class="table table-borderless">
				<tr>
					<td align="right"><select class="form-control" name="key"
						id="key">
							<option value="id" selected="selected">아이디</option>
							<!-- <option value="articleno">글번호</option>
								<option value="subject">제목</option> -->
					</select> <input type="text" class="form-control" placeholder="검색어 입력."
						name="word" id="word">
						<button type="button" id="search" class="btn btn-primary">검색</button></td>
				</tr>
			</table>
		</form>
		<c:if test="${list.size() != 0}">
			<table class="table table-hover">
				<thead class="black white-text">
					<tr>
						<th scope="col">아이디</th>
						<th scope="col">이름</th>
						<th scope="col">닉네임</th>
						<th scope="col">이메일</th>
					</tr>
				</thead>
				<tbody id="memberlist">
					<%-- <c:forEach var="member" items="${list}">
					<tr class="table-info">
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.nickName}</td>
						<td>${member.email}</td>
					</tr>
				</c:forEach> --%>
				</tbody>
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
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$.ajax({
		url : "${root}/member/listAll",
		method : "get",
		contentType : 'application/json; charset=utf-8',
		success : function(members) {
			makelist(members);
		}
	});

	function makelist(members) {
		$("#memberlist").empty();
		console.log(members);
		$(members).each(
				function(index, member) {
					let str = '<tr class="table">' + '	<td>' + member.id
							+ '</td>' + "	<td>" + member.name + "</td>"
							+ "	<td>" + member.nickName + "</td>" + "	<td>"
							+ member.email + "</td>" + "</tr>";
					$("#memberlist").append(str);
				});
	}

	$('#search').on('click', function() {
		let key = $('#key').val();
		let word = $('#word').val();

		if (document.getElementById("word").value == "") {
			alert("모든 목록 조회!!");
		}

		$.ajax({
			url : "${root}/member/searchMember/" + key + "/" + word,
			method : "get",
			contentType : 'application/json; charset=utf-8',
			success : function(members) {
				makelist(members);
			}
		});
	});
</script>
</html>
