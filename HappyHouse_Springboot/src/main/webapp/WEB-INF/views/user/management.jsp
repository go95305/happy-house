<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<jsp:include page="../header.jsp"></jsp:include>
    <div id="wrapper">
        <div id="wrapper-bgbtm" style="width: 700px; margin: 0 auto;">

            <form role="form" method="post" action="${root}/member/modifyMember">
                <div class="form-group" id="divId">
                    <label for="inputId" class="col-lg-2 control-label">아이디</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyAlphabetAndNumber" id="id" data-rule-required="true"
                            placeholder="" maxlength="30" value="${userinfo.id}" readonly="readonly" name="id">
                    </div>
                </div>
                <div class="form-group" id="divPassword">
                    <label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="password" name="pw"
                            data-rule-required="true" placeholder="패스워드" maxlength="30" required="required">
                    </div>
                </div>
                <div class="form-group" id="divPasswordCheck">
                    <label for="inputPasswordCheck" class="col-lg-2 control-label">패스워드
                        확인</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="passwordCheck" data-rule-required="true"
                            placeholder="패스워드 확인" maxlength="30" required="required">
                    </div>
                </div>
                <div class="form-group" id="divName">
                    <label for="inputName" class="col-lg-2 control-label">이름</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyHangul" id="name" data-rule-required="true"
                            placeholder="한글만 입력 가능합니다." maxlength="15" value="" required="required" name="name">
                    </div>
                </div>

                <div class="form-group" id="divNickname">
                    <label for="inputNickname" class="col-lg-2 control-label">별명</label>
                    <div class="col-lg-10">
                        <input type="text" name="nickName" class="form-control" id="nickname" data-rule-required="true" placeholder="별명"
                            maxlength="15" value="" required="required">
                    </div>
                </div>

                <div class="form-group" id="divEmail">
                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                    <div class="col-lg-10">
                        <input type="email" class="form-control" id="email" data-rule-required="true" placeholder="이메일"
                            maxlength="40" value="" required="required" name="email">
                    </div>
                </div>
                <div class="form-group" id="divPhoneNumber">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰
                        번호</label>
                    <div class="col-lg-10">
                        <input type="tel" class="form-control onlyNumber" id="phoneNumber" data-rule-required="true"
                            name="phoneNumber" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11" value="" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="gender" name="gender">
                            <option value="M">남</option>
                            <option value="F">여</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmailReceiveYn" class="col-lg-2 control-label">이메일
                        수신여부</label>
                    <div class="col-lg-10">
                        <label class="radio-inline"> <input type="radio" id="emailReceiveYn" name="emailCheck"
                                value="Y" checked>
                            동의합니다.
                        </label> <label class="radio-inline"> <input type="radio" id="emailReceiveYn"
                                name="emailCheck" value="N"> 동의하지
                            않습니다.
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPhoneNumber" class="col-lg-2 control-label">SMS
                        수신여부</label>
                    <div class="col-lg-10">
                        <label class="radio-inline"> <input type="radio" id="smsReceiveYn" name="phoneCheck" value="Y"
                                checked>
                            동의합니다.
                        </label> <label class="radio-inline"> <input type="radio" id="smsReceiveYn" name="phoneCheck"
                                value="N"> 동의하지
                            않습니다.
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memberInfo" class="col-lg-2 control-label">개인정보취급방침</label>
                    <div class="col-lg-10" id="memberInfo">
                        <div class="radio">
                            <label> <input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" checked> 동의합니다.
                            </label>
                        </div>
                        <div class="radio">
                            <label> <input type="radio" id="memberInfoYn" name="memberInfoYn" value="N"> 동의하지 않습니다.
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="button" id="modified" class="btn btn-primary">정보
                            수정</button>
                        <button type="button" id="remove" class="btn btn-primary">회원
                            탈퇴</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <jsp:include page="../footer.jsp"></jsp:include>
    <script>
        $(function () {
            $('#remove').on('click', function () {
                var result = confirm('Are you sure you want to do this?');

                if (result) {
                    location.href = "${root}/member/deleteMember";
                } else {
                    return;
                }
            });

        });
        
    	$('#modified').on('click', function() {

    		if ($('#password').val() != $('#passwordCheck').val()) {
    			alert("패스워드를 확인하세요.")
    		} else {
    			$('form').submit();
    		}
    	});
    </script>
</body>

</html>