<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Read</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>No : ${center_list.code}</h2>
		<div class="form-group">
			<label for="form">Name: ${center_list.name}</label>
		</div>
		<div class="form-group">
			<label for="form">State: ${center_list.state}</label>
		</div>
		<div class="form-group">
			<label for="form">address:</label>
			<textarea rows="20" cols="100" id="contents" readonly="readonly">${board.address}</textarea>
		</div>
		<div class="form-group">
			<label class="form">Created Time : ${center_list.created_date} </label> 
			<label class="form">Modified Time : ${center_list.update_date} </label>
		</div>
		<div>
		<a href="/board"><button type="button" class="btn btn-info">Move to List</button></a>
		<a href="/board/update/${board.code}"><button type="button" class="btn btn-success">Update</button></a>
		</div>
	</div>
</body>
</html>