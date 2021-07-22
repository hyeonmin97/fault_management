<%@page import="com.example.demo.Jpatest.dto.SessionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
 <link rel="stylesheet" href="resources/css/index.css">
</head>
<body>
<div class ="ma">
		<div id="text"> 
			<div class= "login">
				<button type="button" onClick="location.href='/userLogin';">로그인</button> 
			</div>
			<div class= "logout">
				<button type="button" onClick="location.href='/logout';">로그아웃</button> 
			</div>
		</div>
		
	<br>		
	<hr>
	<br><br><br><br><br><br><br><br><br><br><br><br> <!-- 일단 이렇게 보류-->
	
		<%
		   try{
		      SessionUser ssu = (SessionUser)session.getAttribute("user");
		      if(java.util.Objects.isNull(ssu)){
		%>
		         <h1></h1>
		
		<%
		      }
		      else{
		%>
		     <h1></h1>
		         <!-- 로그아웃 누르면 로그아웃 페이지도 호출해야함 -->
		         <a href="/logout" onclick="javascript:newin=window.open('about:blank'); newin.location.href='https://mail.google.com/mail/u/0/?logout&hl=en';">구글 로그아웃</a>
		<%
		      }
		%>
		
		<%
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		%>

		<div id="btn_group"> 
			<div class= "btn1">
				<button type="button" onClick="location.href='/user';">유저권한 페이지 이동</button> 
			</div>
			<br>
			<div class= "btn2">
				<button type="button" onClick="location.href='/home';">어드민권한 페이지 이동</button> 
			</div>
			<br>
		</div>		
	<hr>
	
		<div>
			<a href="https://www.instagram.com/">
			<img src="resources/img/ins.png" width: "50px" height="50px"></img>
			</a>
			<a href="https://www.youtube.com/">
			<img src="resources/img/you.png" width: "50px" height="50px"></img>
			</a>
			<a href="https://twitter.com/?lang=ko">
			<img src="resources/img/tw.png" width: "50px" height="50px"></img>
			</a>
		</div>
		
</div>
</body>
</html>