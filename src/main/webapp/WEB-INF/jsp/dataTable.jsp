<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css"/>

<title>Insert title here</title>

<script>
var column = [ "col1", "col2" ];

$(document).ready(function() {
	$("#example").DataTable({
		"processing": true,
	    "serverSide": true,
	    //"length" : 10,
	    "ajax": {
	        "url": "/dataTable",
	        "type": "POST",
	        "dataType": "JSON",
	        "dataSrc":'',
	        "data" : {
		        test : "1111"
		    },
	    },
	    "columns" : [
	        {"data": "address"},
	        {"data": "detailAddress"},
	        {"data": "faxNumber"},
	        {"data": "name"},
	        {"data": "phoneNumber"},
	        {"data": "postalCode"},
	        {"data": "state"}
	    ]	 
	});
});
</script>

</head>
<body>
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>address</th>
                <th>detail_address</th>
                <th>fax_number</th>
                <th>name</th>
                <th>phone_number</th>
                <th>postal_code</th>
                <th>state</th>
            </tr>
        </thead>
        
    </table>
</body>
</html>
