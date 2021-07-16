<!-- 회원가입 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<link rel="stylesheet" href="resources/css/signUp.css">
<head>
<title>회원가입 페이지</title>

	<table align="center">
	  <tr>
	    <td><img src="resources/img/person.png" width="100px" height="70px" /></td> 
	    <td class="head">WEB</td>
	  <tr>
	 </table>
</head>
<body>
<form action="/signup" method="post">

	<div class="out">
				
		<div class="input">
			이메일&nbsp;&nbsp;&nbsp;
			<input type="text" name="email" >
		</div>
		
		<div class="input">
			암호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="password" name="password">
		</div>
		<input type="submit" value="회원가입">
		<input type=reset value="취소">	
	</div>
		<br><br><br>
		
		<table align="center">
		  <tr>
			 <td >이용약관 |</td>
			 <td class="foot">개인정보처리방침</td>
			 <td >| 책임의 한계와 법적고지 |</td>
			 <td >회원정보 |</td>
			 <td >고객센터</td>
			 <tr>
		</table>
 
</form>
</body>
</html>