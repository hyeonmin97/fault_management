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
	    "serverSide": false,
	    //"length" : 10,
	    "ajax": {
	        "url": "/dataTable",
	        "type": "POST",
	        "dataType": "JSON",
	        "data" : {
		        test : "1111"
		    },
	    },
	    "columns" : [
	        {"data": "col1"},
	        {"data": "col2"}
	    ]	    
	});
});
</script>

</head>
<body>
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>col1</th>
                <th>col2</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>col1</th>
                <th>col2</th>
            </tr>
        </tfoot>
    </table>
</body>
</html>