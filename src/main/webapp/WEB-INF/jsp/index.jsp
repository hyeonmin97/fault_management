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
<a href="/userLogin">�α���</a>


<a href="/logout">�α׾ƿ�</a>
<br>

<%
	try{
		SessionUser ssu = (SessionUser)session.getAttribute("user");
		if(java.util.Objects.isNull(ssu)){
%>
			���µ���

<%
		}
		else{
%>
			�ִµ���
			<!-- �α׾ƿ� ������ �α׾ƿ� �������� ȣ���ؾ��� -->
			<a href="/logout" onclick="javascript:newin=window.open('about:blank'); newin.location.href='https://mail.google.com/mail/u/0/?logout&hl=en';">���� �α׾ƿ�</a>
<%
		}
%>

<%
	}catch(Exception e){
		e.printStackTrace();
	}
%>




<div></div>
<a href="/user">�������� ������ �̵�</a>
<a href="/admin">���α��� ������ �̵�</a>
</body>
</html>