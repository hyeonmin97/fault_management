<!-- 회원가입 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>회원가입 페이지</title>
<link rel="stylesheet" href="/resources/css/signUp.css">
</head>
<body>
<form action="signup" method="post">

	<div class="out">
		<div class="logo" >
              <a href="index.jsp"><img src="file//logo.png" alt=""></a>
          </div>
 		
		<div class="input">
			이메일&nbsp;&nbsp;&nbsp;
			<input type="text" name="email" >
		</div>
		
		<div class="input">
			암호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="password" name="password">
		</div>
		
		
		
		<div class="input">
			권한
			<input type="radio" name="auth" value="ROLE_ADMIN,ROLE_USER"> admin
			<input type="radio" name="auth" value="ROLE_USER"> user
		</div>
		<input type="submit" value="회원가입">
		<input type=reset value="취소">
	
	</div>
 
		
</form>
</body>
</html>