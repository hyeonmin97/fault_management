<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
 <head>
	<meta charset="UTF-8">
  <title>관리자 홈</title>
 </head>
 <body>
  		<tiles:insertAttribute name="header" />
  		<hr>
		<div style="border:1px solid; padding:10px; background: #E6E6E6;">
		테이블 들어올 곳
		</div>
  		<hr>
  		<tiles:insertAttribute name="footer" />
</body>
</html>