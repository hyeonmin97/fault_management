<%@page import="com.example.demo.Jpatest.dto.SessionUser"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

index
<a href="/userLogin">로그인</a>


<a href="/logout">로그아웃</a>
<br>

<%
	try{
		SessionUser ssu = (SessionUser)session.getAttribute("user");
		if(java.util.Objects.isNull(ssu)){
%>
			없는데용

<%
		}
		else{
%>
			있는데용
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




<div></div>
<a href="/user">유저권한 페이지 이동</a>
<a href="/admin">어드민권한 페이지 이동</a>
</body>
</html>