<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>查询成功</h1>
	<%
		System.out.println((String)session.getAttribute("value"));
		out.println((String)session.getAttribute("value"));
	%>
</body>
</html>