<!-- 로그인 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>로그인 페이지</title>
<link rel="stylesheet" href="resources/css/login.css">
</head>
  <body>
    <div class="out">
          
		<div class="form">
        
			<form action="/userLogin" method="post">
				<div class="login" >
					<input type="text" name="username" size="10" placeholder="아이디" autocomplete="off" >
				</div>
				<div class="login" >
					<input type="password" name="password" size="10" placeholder="비밀번호" >
				</div>
				<div class="login" >
					<button type="submit" class="loginButton">로그인</button><br>
				</div>
			</form>
			
			<div class = "oauthButton">
				<a href="oauth2/authorization/google">
	 				<img src="resources/img/google.png"></img>
	 			</a>
 			</div>
			
          
        </div>
        
       	
       	
        <div class="join">
          <a href="/signup">회원가입</a>
        </div> 
 		
 		
     </div>


  </body>
</html>
