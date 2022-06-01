<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("amt",123321123.126);
		request.setAttribute("now",new java.util.Date());
		request.setAttribute("html","<a href='index.html'>index</a>");
		request.setAttribute("nothing",null);
	%>
	<!-- formateDate Pattern
	yyyy 四位年
	MM 两位月
	dd 两位日
	HH 24小时
	hh 12小时
	mm 分钟
	ss 秒
	SSS 毫秒 -->
	<h2>${requestScope.now}</h2>
	<h2>
	<fmt:formatDate value="${requestScope.now}" pattern="yyyy年MM月dd日 HH时MM分钟ss秒数SSS毫秒 "></fmt:formatDate>
	</h2>
	<h2>${requestScope.amt}</h2>
	<h2>
		<fmt:formatNumber value="${requestScope.amt}" pattern="0,00 0.00"></fmt:formatNumber>元
	</h2>
	<h2>null默认值: 
	<c:out value="${requestScope.nothing}" default="无"></c:out>
	</h2>
	<h2>${requestScope.html}</h2>
	<h2><c:out value="${requestScope.html}" escapeXmlz="true"></c:out></h2>
</body>
</html>