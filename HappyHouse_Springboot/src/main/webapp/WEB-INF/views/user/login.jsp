<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrapper" style="height: 600px;">
		<div id="wrapper-bgbtm">

			<div class="container" style="background-color: white;">
				<div class="row">
					<div class="col-sm-4" style="margin: auto;">
						<div class="card">
							<article class="card-body">
								<c:if test="${userinfo ne null}">
									<h4><Strong>${userinfo.id} 님 안녕하세요!</Strong></h4>
									<br>
									<button type="button" id="signup"
										class="btn btn-primary btn-block">회원정보 수정</button>
								</c:if>
								<c:if test="${userinfo eq null}">
									<h4 class="card-title mb-4 mt-1">Sign in</h4>
									<form action="${root}/member/login" method="post">	
										<div class="form-group">
											<label>아이디</label> <input id="id" name="id"
												class="form-control" placeholder="Id" type="text">
										</div>
										<!-- form-group// -->
										<div class="form-group">
											<a class="float-right" href="#"></a> <label>비밀번호</label>
											<input class="form-control" placeholder="******"
												type="password" name="pw" id="pw">
										</div>
										<!-- form-group// -->
										<div class="form-group">
											<div class="checkbox">
												<label> <input type="checkbox"> 비밀번호 저장
												</label>
											</div>
											<!-- checkbox .// -->
										</div>
										<!-- form-group// -->
										<div class="form-group">
											<button type="button" id="login"
												class="btn btn-primary btn-block">로그인</button>
											<button type="button" id="signup"
												class="btn btn-primary btn-block">회원가입</button>
											<button type="button" id="pwdsearch"
												class="btn btn-primary btn-block" data-toggle="modal"
												data-target="#myModal">비밀번호 찾기</button>
										</div>
										<!-- form-group// -->
									</form>
								</c:if>
							</article>
						</div>
						<!-- card.// -->

					</div>
					<!-- col.// -->
				</div>
				<!-- row.// -->

			</div>
			<!--container end.//-->
		</div>
	</div>

	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 찾기</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-group">
						<label>Your Id</label> <input id="id" name="id"
							class="form-control" placeholder="Id" type="text"
							required="required">
					</div>
					<div class="form-group" id="divEmail">
						<label for="inputEmail" class="control-label">이메일</label>
						<div class="form-group">
							<input type="email" class="form-control" id="email"
								data-rule-required="true" placeholder="이메일" maxlength="40"
								required="required">
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="submit" id='confirm' class="btn btn-primary"
						data-dismiss="modal">확인</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
				</div>

			</div>
		</div>
	</div>


	<jsp:include page="../footer.jsp"></jsp:include>
	<script>
		$(function() {
			$('#confirm').on('click', function() {
				alert("메일이 전송되었습니다.");
			})
		});
		
		$(function() {
			$('#login').on('click', function() {
				let id = $('#id').val();
				let pw = $('#pw').val();

				console.log(id + " : " + pw);
				if (!$('#id').val() || !$('#pw').val()) {
					alert("Id와 Password를 확인하세요");
					return;
				}

				$('form').submit();

			});

			$('#signup').on('click', function() {
				location.href = "${root}/signup";
			});
		});
	</script>
</body>

</html>