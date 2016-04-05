<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>메인 화면</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body>
	<img src="${context}/resources/img/main/index.gif" id="image" alt="" style="margin-left: 33%; margin-top: 15%" />
	<%-- <a href="${context}/member/login_form.do">로그인 화면 이동</a> <!-- 이 전체를 url이라고 하고 /member/Login_form.do는 uri라고 한다.  --> --%>
</body>
<script type="text/javascript"> /* 스크립트 코드는 여기에 써주는게 빠르다! */
	$(document).ready(function() { // 얘는 웹 어플리케이션이 실행되면 바로 실행되는 메인 메소드 같은 존재이다.
		location.href = '${context}/global/main.do'; // 맨 처음에 이 이미지가 보여지고 자동으로 메인화면으로 넘어간다.
	});
</script>
</html>


