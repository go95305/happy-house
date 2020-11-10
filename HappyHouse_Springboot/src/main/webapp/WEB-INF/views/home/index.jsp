<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(
			function() {
				$.get("${pageContext.request.contextPath}/guestbook/subMain",
						function(data, status) {
							$("#noticeList").empty();
							$.each(data, function(index, vo) {
								console.log(vo.content);
								let str = '<li>'
										+ '<strong><p class="noticeContent">'
										+ vo.subject + '<strong>' + '</p>'
										+ '<span class="writer">' + "작성자: "
										+ vo.userid + '</span>' + '<br/>'
										+ '<span class="date">' + "작성 날짜: "
										+ vo.regtime + '</span>' + '</li>'
								$('#noticeList').append(str);
							});
						});
			});
</script>
<div id="wrapper">
	<div id="wrapper-bgbtm">
		<div id="featured" class="container">
			<div id="fbox1">
				<a href="${root}/priceview"><img
					src="${root}/resources/img/pic01.jpg" alt="" /></a>
				<h2>실거래가 비교</h2>
				<p>원하는 지역 가격 정보를 한 눈에 비교 분석! 업계 최저가로 계약하세요.</p>
				<a href="${root}/priceview" class="button"><span>More
						Info</span></a>
			</div>
			<div id="fbox2">
				<c:if test="${userinfo eq null }">
					<a href="${root}/priceview">
				</c:if>
				<c:if test="${userinfo ne null }">
					<a href="${root}/interest/showStar">
				</c:if>
				<img src="${root}/resources/img/pic02.jpg" alt="" /></a>
				<h2>관심지역 조회</h2>
				<p>내가 설정한 관심지역을 조회해 보세요. 지하철역부터 대형마트까지! 직접 가보지 않아도 쉽게 알아보세요.</p>
				<a href="${root}/interest/showStar" class="button"><span>More
						Info</span></a>
			</div>
			<div id="fbox3">
				<a href="/happyhouse/star_view.jsp"><img
					src="${root}/resources/img/pic03.jpg" alt="" /></a>
				<h2>관심지역 업소정보 확인</h2>
				<p>주변의 편의시설들을 편하게 검새해 보세요. 지하철역부터 대형마트까지! 직접 가보지 않아도 쉽게 알아보세요.</p>
				<a href="#" class="button"><span>More Info</span></a>
			</div>
		</div>
		<div id="page" class="container">
			<div id="content">
				<h2>Libero volutpat pulvinar</h2>
				<span class="byline">Gravida sed consequat lorem ipsum dolor</span>
				<p>
					<img src="${root}/resources/img/pic04.jpg" width="700" height="200"
						alt="" />Pellentesque viverra vulputate enim. Aliquam erat
					volutpat. Pellentesque tristique ante ut risus. Quisque dictum.
					Integer nisl risus, sagittis convallis, rutrum id, elementum
					congue, nibh. Suspendisse dictum porta lectus. Donec placerat odio
					vel elit. Nullam ante orci, pellentesque eget, tempus quis,
					ultrices in, est. Curabitur sit amet nulla. Nam in massa. Sed vel
					tellus. Curabitur sem urna, consequat vel, suscipit in, mattis
					placerat, nulla. Sed ac leo. Pellentesque imperdiet. Donec leo.
					Vivamus fermentum nibh in augue.
				</p>
				<a href="#" class="button2">More Info</a>
			</div>
			<div id="sidebar">
				<div>
					<h2>공지사항</h2>
					<ul class="style1" id="noticeList">
					</ul>
					<a href="${root}/guestbook/main?pg=1&key=&word=" class="button2">More
						Info</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>