<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	String url = request.getRequestURL().toString(); //HttpServletResquest 
		response.getWriter().println(url);  //HttpServletResponse 
	%>
	<% out.println("AAAABBB");
	%>
	<% out.println((String)application.getInitParameter("copyright"));
	%>
	<%
		 
	%>
</body>
</html>